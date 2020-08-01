package com.senlainc.message;

import com.senlainc.transfer.dto.MessageDTO;
import com.senlainc.transfer.dto.UserDTO;

import java.util.List;

public interface MessageService {

    MessageDTO getMessage(long id);

    long addMessage(MessageDTO message);

    void deleteMessage(long id);

    List<MessageDTO> getAllMessages(long userIdTo, long userIdFrom);

    List<UserDTO> getIncomingUserList(long userIdTo);


}
