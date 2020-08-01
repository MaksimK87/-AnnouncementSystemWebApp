package com.senlainc.transfer.mapper;

import com.senlainc.entity.AnnouncementCategory;
import com.senlainc.transfer.dto.AnnouncementCategoryDTO;
import org.springframework.stereotype.Component;

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
public class AnnouncementCategoryMapperImpl implements AnnouncementCategoryMapper {

    @Override
    public AnnouncementCategoryDTO toDTO(AnnouncementCategory announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementCategoryDTO announcementCategoryDTO = new AnnouncementCategoryDTO();

        announcementCategoryDTO.setIdCategory( announcement.getIdCategory() );
        announcementCategoryDTO.setCategory( announcement.getCategory() );

        return announcementCategoryDTO;
    }

    @Override
    public AnnouncementCategory toEntity(AnnouncementCategoryDTO announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        AnnouncementCategory announcementCategory = new AnnouncementCategory();

        announcementCategory.setIdCategory( announcementDTO.getIdCategory() );
        announcementCategory.setCategory( announcementDTO.getCategory() );

        return announcementCategory;
    }

    @Override
    public List<AnnouncementCategoryDTO> toDTO(List<AnnouncementCategory> announcement) {
        if ( announcement == null ) {
            return null;
        }

        List<AnnouncementCategoryDTO> list = new ArrayList<AnnouncementCategoryDTO>( announcement.size() );
        for ( AnnouncementCategory announcementCategory : announcement ) {
            list.add( toDTO( announcementCategory ) );
        }

        return list;
    }

    @Override
    public List<AnnouncementCategory> toEntity(List<AnnouncementCategoryDTO> announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        List<AnnouncementCategory> list = new ArrayList<AnnouncementCategory>( announcementDTO.size() );
        for ( AnnouncementCategoryDTO announcementCategoryDTO : announcementDTO ) {
            list.add( toEntity( announcementCategoryDTO ) );
        }

        return list;
    }
}
