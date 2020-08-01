package com.senlainc.message;

import com.senlainc.entity.Message;
import com.senlainc.repository.message.MessageReposirory;
import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.user.UserService;
import com.senlainc.transfer.dto.MessageDTO;
import com.senlainc.transfer.dto.UserDTO;
import com.senlainc.transfer.mapper.MessageMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    MessageReposirory messageReposirory;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserService userService;

    @Override
    public MessageDTO getMessage(long id) {
        Message message = messageReposirory.getMessage(id).orElseThrow(() ->
                new RecordNotFoundException("message with id: " + id + " doesn't exist"));
        logger.debug("Message by id: "+message);

        return messageMapper.toDTO(message);
    }

    @Override
    public long addMessage(MessageDTO messageDTO) {
        Message message = messageMapper.toEntity(messageDTO);
        logger.debug("Message from message DTO: "+message);
        Calendar calendar = Calendar.getInstance();
        message.setPublicationDate(calendar.getTime());
        logger.debug("Send message: " + message);
        return messageReposirory.addMessage(message);
    }

    @Override
    public void deleteMessage(long id) {
        messageReposirory.deleteMessage(id);

    }

    public List<MessageDTO> getAllMessages(long userIdTo, long userIdFrom) {
        List<Message> messages = messageReposirory.getAllMessages(userIdTo, userIdFrom).orElseThrow(() ->
                new RecordNotFoundException("Messages for user id: " + userIdTo + "from id: " + userIdFrom + " didn't " +
                        "found"));

        return messageMapper.toDTO(messages);
    }

    @Override
    public List<UserDTO> getIncomingUserList(long userIdTo) {
        List<Long> incomingListId;
        List<UserDTO> users = new ArrayList<>();
        incomingListId = messageReposirory.getIncomingUserList(userIdTo).orElseThrow(() -> new RecordNotFoundException("There are" +
                "no users write to user with id: " + userIdTo));

        for (Long id : incomingListId) {
            users.add(userService.findUserById(id));
        }
        return users;
    }

}
