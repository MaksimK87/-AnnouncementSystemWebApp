package com.senlainc.user;

import com.senlainc.exceptions.SuchUserExistsException;
import com.senlainc.transfer.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findUserById(Long userId);

    List<UserDTO> allUsers();

    UserDTO saveUser(UserDTO userDTO) throws SuchUserExistsException;

    boolean deleteUser(Long userId);

    void update(UserDTO userDTO);

}
