package com.senlainc.transfer.mapper;

import com.senlainc.entity.Role;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {CreditCardMapper.class, MessageMapper.class, AnnouncementMapper.class, CommentMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOtoUser(UserDTO userDTO);

    List<User> toEntity(List<UserDTO> userDTOs);

    List<UserDTO> toDTO(List<User> users);

    default Set<Role> getRoles(UserDTO userDTO) {

        return userDTO.getRoles();
    }
}
