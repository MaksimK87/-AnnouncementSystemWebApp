package com.senlainc.transfer.mapper;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.AnnouncementCategory;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.AnnouncementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-21T09:26:53+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
*/
@Component
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public AnnouncementDTO toDto(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementDTO announcementDTO = new AnnouncementDTO();

        Integer idCategory = announcementAnnouncementCategoryIdCategory( announcement );
        if ( idCategory != null ) {
            announcementDTO.setAnnouncementCategoryId( idCategory.longValue() );
        }
        announcementDTO.setCreationDate( dateToString( announcement.getCreationDate() ) );
        announcementDTO.setPaymentDate( dateToString( announcement.getPaymentDate() ) );
        announcementDTO.setUserId( announcementUserId( announcement ) );
        announcementDTO.setIdAnnouncement( announcement.getIdAnnouncement() );
        announcementDTO.setHeader( announcement.getHeader() );
        announcementDTO.setDescription( announcement.getDescription() );
        announcementDTO.setTopStatus( announcement.isTopStatus() );
        announcementDTO.setActiveStatus( announcement.isActiveStatus() );
        announcementDTO.setItemPrice( announcement.getItemPrice() );
        announcementDTO.setComments(commentMapper.toDTO( announcement.getComments() ) );

        return announcementDTO;
    }

    @Override
    public Announcement toEntity(AnnouncementDTO announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setUser( announcementDTOToUser( announcementDTO ) );
        announcement.setAnnouncementCategory( announcementDTOToAnnouncementCategory( announcementDTO ) );
        try {
            announcement.setCreationDate( stringToDate( announcementDTO.getCreationDate() ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        try {
            announcement.setPaymentDate( stringToDate( announcementDTO.getPaymentDate() ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        announcement.setIdAnnouncement( announcementDTO.getIdAnnouncement() );
        announcement.setHeader( announcementDTO.getHeader() );
        announcement.setDescription( announcementDTO.getDescription() );
        announcement.setTopStatus( announcementDTO.isTopStatus() );
        announcement.setActiveStatus( announcementDTO.isActiveStatus() );
        announcement.setItemPrice( announcementDTO.getItemPrice() );
        announcement.setComments( commentMapper.toEntity( announcementDTO.getComments() ) );

        return announcement;
    }

    @Override
    public List<Announcement> toEntity(List<AnnouncementDTO> announcementDTOS) {
        if ( announcementDTOS == null ) {
            return null;
        }

        List<Announcement> list = new ArrayList<Announcement>( announcementDTOS.size() );
        for ( AnnouncementDTO announcementDTO : announcementDTOS ) {
            list.add( toEntity( announcementDTO ) );
        }

        return list;
    }

    @Override
    public List<AnnouncementDTO> toDTO(List<Announcement> announcements) {
        if ( announcements == null ) {
            return null;
        }

        List<AnnouncementDTO> list = new ArrayList<AnnouncementDTO>( announcements.size() );
        for ( Announcement announcement : announcements ) {
            list.add( toDto( announcement ) );
        }

        return list;
    }

    private Integer announcementAnnouncementCategoryIdCategory(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }
        AnnouncementCategory announcementCategory = announcement.getAnnouncementCategory();
        if ( announcementCategory == null ) {
            return null;
        }
        int idCategory = announcementCategory.getIdCategory();
        return idCategory;
    }

    private Long announcementUserId(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }
        User user = announcement.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User announcementDTOToUser(AnnouncementDTO announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( announcementDTO.getUserId() );

        return user;
    }

    protected AnnouncementCategory announcementDTOToAnnouncementCategory(AnnouncementDTO announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        AnnouncementCategory announcementCategory = new AnnouncementCategory();

        if ( announcementDTO.getAnnouncementCategoryId() != null ) {
            announcementCategory.setIdCategory( announcementDTO.getAnnouncementCategoryId().intValue() );
        }

        return announcementCategory;
    }
}
