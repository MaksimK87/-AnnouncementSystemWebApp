package com.senlainc.transfer.mapper;

import com.senlainc.entity.Message;
import com.senlainc.transfer.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper {
    MessageMapper INSTSNCE = Mappers.getMapper(MessageMapper.class);

    @Mappings({@Mapping(target = "publicationDate", source = "publicationDate", dateFormat = "HH:mm dd-MM-yyyy"),
            @Mapping(target = "userIdTo", source = "user.id")})
    MessageDTO toDTO(Message message);

    @Mapping(source = "userIdTo", target = "user.id")
    Message toEntity(MessageDTO messageDTO);

    List<Message> toEntity(List<MessageDTO> messageDTOS);

    List<MessageDTO> toDTO(List<Message> messages);

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
