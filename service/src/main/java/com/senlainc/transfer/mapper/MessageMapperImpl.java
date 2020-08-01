package com.senlainc.transfer.mapper;

import com.senlainc.entity.Message;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.MessageDTO;
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
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO toDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setUserIdTo( messageUserId( message ) );
        messageDTO.setPublicationDate( dateToString( message.getPublicationDate() ) );
        messageDTO.setIdMessage( message.getIdMessage() );
        messageDTO.setMessage( message.getMessage() );
        messageDTO.setUserIdFrom( message.getUserIdFrom() );

        return messageDTO;
    }

    @Override
    public Message toEntity(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setUser( messageDTOToUser( messageDTO ) );
        message.setIdMessage( messageDTO.getIdMessage() );
        message.setMessage( messageDTO.getMessage() );
        try {
            message.setPublicationDate( stringToDate( messageDTO.getPublicationDate() ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        if ( messageDTO.getUserIdFrom() != null ) {
            message.setUserIdFrom( messageDTO.getUserIdFrom() );
        }

        return message;
    }

    @Override
    public List<Message> toEntity(List<MessageDTO> messageDTOS) {
        if ( messageDTOS == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( messageDTOS.size() );
        for ( MessageDTO messageDTO : messageDTOS ) {
            list.add( toEntity( messageDTO ) );
        }

        return list;
    }

    @Override
    public List<MessageDTO> toDTO(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDTO( message ) );
        }

        return list;
    }

    private Long messageUserId(Message message) {
        if ( message == null ) {
            return null;
        }
        User user = message.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User messageDTOToUser(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( messageDTO.getUserIdTo() );

        return user;
    }
}
