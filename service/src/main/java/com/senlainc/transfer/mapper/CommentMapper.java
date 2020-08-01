package com.senlainc.transfer.mapper;

import com.senlainc.entity.Comment;
import com.senlainc.transfer.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnnouncementMapper.class, UserMapper.class})
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({@Mapping(source = "announcement.idAnnouncement", target = "announcementId"), @Mapping(source = "user.id",
            target = "userId"), @Mapping(target = "publicationDate", source = "publicationDate", dateFormat = "HH:mm dd-MM-yyyy")})
    CommentDTO toDTO(Comment comment);

    @Mappings({@Mapping(source = "announcementId", target = "announcement.idAnnouncement"), @Mapping(source = "userId",
            target = "user.id"), @Mapping(target = "publicationDate", source = "publicationDate", dateFormat = "HH:mm dd-MM-yyyy")})
    Comment toEntity(CommentDTO commentDTO);

    List<Comment> toEntity(List<CommentDTO> commentDTOs);

    List<CommentDTO> toDTO(List<Comment> comments);

}
