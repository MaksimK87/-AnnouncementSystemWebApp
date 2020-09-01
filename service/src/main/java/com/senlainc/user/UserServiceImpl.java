package com.senlainc.user;

import com.senlainc.entity.Role;
import com.senlainc.entity.User;
import com.senlainc.exceptions.PasswordMisMatchException;
import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.exceptions.SuchUserExistsException;
import com.senlainc.repository.user.UserRepository;
import com.senlainc.transfer.dto.UserDTO;
import com.senlainc.transfer.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserMapper userMapper;

    public UserDTO findUserById(Long userId) {
        UserDTO userDTO;
        Optional<User> userFromDb = userRepository.findById(userId);
        if (userFromDb.isPresent()) {
            logger.debug("find user: " + userFromDb.get());
            User user = userFromDb.get();
            user.setPasswordConfirm(user.getPassword());
            userDTO = userMapper.userToUserDTO(user);
            return userDTO;
        } else {
            throw new RecordNotFoundException("User with id: {" + userId + "} does't exist");
        }
    }

    public List<UserDTO> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User u : users) {
            userDTOs.add(userMapper.userToUserDTO(u));
        }
        return userDTOs;
    }

    public UserDTO saveUser(UserDTO userDTO) {

        User userFromDB;
        User user;
        Long id;

        if (!(userDTO.getPassword().equals(userDTO.getPasswordConfirm()))) {
            throw new PasswordMisMatchException("password mismatch, check entered password");
        }

        userFromDB = userRepository.findByUsername(userDTO.getUsername());

        if (userFromDB != null) {
            throw new SuchUserExistsException("User with such name: " + userDTO.getUsername() + " exists!");
        }

        user = userMapper.userDTOtoUser(userDTO);

        logger.info("user from dtoUser: " + user);

        logger.debug("user's role: " + user.getRoles());

        if (user.getRoles() != null && user.getRoles().contains(new Role(2L, "ROLE_ADMIN"))) {

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            id = userRepository.save(user);
            logger.debug("Save new admin, id: " + id);

        } else {

            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            id = userRepository.save(user);
            logger.debug("Save new user, id: " + id);

        }

        user.setId(id);

        return userMapper.userToUserDTO(user);
    }

    public boolean deleteUser(Long userId) {

        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    public void update(UserDTO userDTO) {

        if (!(userDTO.getPassword().equals(userDTO.getPasswordConfirm()))) {
            throw new PasswordMisMatchException("password mismatch, check entered password");
        }

        User user;

        user = userMapper.userDTOtoUser(userDTO);

        logger.info("user from dtoUser: " + user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.update(user);
    }


}
