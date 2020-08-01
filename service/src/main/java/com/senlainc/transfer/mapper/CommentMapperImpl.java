package com.senlainc.transfer.mapper;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.Comment;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-21T09:26:52+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
*/
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public CommentDTO toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setAnnouncementId( commentAnnouncementIdAnnouncement( comment ) );
        commentDTO.setUserId( commentUserId( comment ) );
        commentDTO.setPublicationDate( announcementMapper.dateToString( comment.getPublicationDate() ) );
        commentDTO.setIdComment( comment.getIdComment() );
        commentDTO.setCommentText( comment.getCommentText() );

        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setUser( commentDTOToUser( commentDTO ) );
        comment.setAnnouncement( commentDTOToAnnouncement( commentDTO ) );
        try {
            comment.setPublicationDate( announcementMapper.stringToDate( commentDTO.getPublicationDate() ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        comment.setIdComment( commentDTO.getIdComment() );
        comment.setCommentText( commentDTO.getCommentText() );

        return comment;
    }

    @Override
    public List<Comment> toEntity(List<CommentDTO> commentDTOs) {
        if ( commentDTOs == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( commentDTOs.size() );
        for ( CommentDTO commentDTO : commentDTOs ) {
            list.add( toEntity( commentDTO ) );
        }

        return list;
    }

    @Override
    public List<CommentDTO> toDTO(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toDTO( comment ) );
        }

        return list;
    }

    private Long commentAnnouncementIdAnnouncement(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Announcement announcement = comment.getAnnouncement();
        if ( announcement == null ) {
            return null;
        }
        long idAnnouncement = announcement.getIdAnnouncement();
        return idAnnouncement;
    }

    private Long commentUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User commentDTOToUser(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( commentDTO.getUserId() );

        return user;
    }

    protected Announcement commentDTOToAnnouncement(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        if ( commentDTO.getAnnouncementId() != null ) {
            announcement.setIdAnnouncement( commentDTO.getAnnouncementId() );
        }

        return announcement;
    }
}
