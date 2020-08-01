package com.senlainc.transfer.mapper;

import com.senlainc.entity.Role;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-21T09:26:52+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
*/
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private CreditCardMapper creditCardMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setPasswordConfirm( user.getPasswordConfirm() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRegion( user.getRegion() );
        userDTO.setRating( user.getRating() );
        userDTO.setCity( user.getCity() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDTO.setRoles( new HashSet<Role>( set ) );
        }
        userDTO.setCreditCards( creditCardMapper.toDTO( user.getCreditCards() ) );
        userDTO.setMessages( messageMapper.toDTO( user.getMessages() ) );
        userDTO.setAnnouncements( announcementMapper.toDTO( user.getAnnouncements() ) );
        userDTO.setComments( commentMapper.toDTO( user.getComments() ) );

        return userDTO;
    }

    @Override
    public User userDTOtoUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setPasswordConfirm( userDTO.getPasswordConfirm() );
        Set<Role> set = userDTO.getRoles();
        if ( set != null ) {
            user.setRoles( new HashSet<Role>( set ) );
        }
        user.setEmail( userDTO.getEmail() );
        user.setRating( userDTO.getRating() );
        user.setRegion( userDTO.getRegion() );
        user.setCity( userDTO.getCity() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setCreditCards( creditCardMapper.toEntity( userDTO.getCreditCards() ) );
        user.setMessages( messageMapper.toEntity( userDTO.getMessages() ) );
        user.setAnnouncements( announcementMapper.toEntity( userDTO.getAnnouncements() ) );
        user.setComments( commentMapper.toEntity( userDTO.getComments() ) );

        return user;
    }

    @Override
    public List<User> toEntity(List<UserDTO> userDTOs) {
        if ( userDTOs == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOs.size() );
        for ( UserDTO userDTO : userDTOs ) {
            list.add( userDTOtoUser( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDTO(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( userToUserDTO( user ) );
        }

        return list;
    }
}
