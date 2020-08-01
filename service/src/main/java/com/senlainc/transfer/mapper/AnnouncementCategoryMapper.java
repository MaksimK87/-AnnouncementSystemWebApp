package com.senlainc.transfer.mapper;

import com.senlainc.entity.AnnouncementCategory;
import com.senlainc.transfer.dto.AnnouncementCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnnouncementMapper.class})

public interface AnnouncementCategoryMapper {

    AnnouncementCategoryMapper INSTANCE = Mappers.getMapper(AnnouncementCategoryMapper.class);


    AnnouncementCategoryDTO toDTO(AnnouncementCategory announcement);

    AnnouncementCategory toEntity(AnnouncementCategoryDTO announcementDTO);

    List<AnnouncementCategoryDTO> toDTO(List<AnnouncementCategory> announcement);

    List<AnnouncementCategory> toEntity(List<AnnouncementCategoryDTO> announcementDTO);
}
