package com.senlainc.transfer.mapper;

import com.senlainc.entity.Announcement;
import com.senlainc.transfer.dto.AnnouncementDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, AnnouncementCategoryMapper.class, UserMapper.class})
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    @Mappings({@Mapping(target = "creationDate", source = "announcement.creationDate", dateFormat = "HH:mm dd-MM-yyyy"),
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "announcementCategoryId", source = "announcementCategory.idCategory"),
            @Mapping(target = "paymentDate", source = "announcement.paymentDate", dateFormat = "HH:mm dd-MM-yyyy")})
    AnnouncementDTO toDto(Announcement announcement);

    @InheritInverseConfiguration
    Announcement toEntity(AnnouncementDTO announcementDTO);

    List<Announcement> toEntity(List<AnnouncementDTO> announcementDTOS);

    List<AnnouncementDTO> toDTO(List<Announcement> announcements);


    default Announcement fromId(Long id) {

        if (id == null) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setIdAnnouncement(id);

        return announcement;
    }

    default String dateToString(Date creationDate) {
        if (creationDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            return dateFormat.format(creationDate);
        }
        return null;
    }

    default Date stringToDate(String creationDate) throws ParseException {
        if (creationDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            return dateFormat.parse(creationDate);
        }
        return null;

    }
}