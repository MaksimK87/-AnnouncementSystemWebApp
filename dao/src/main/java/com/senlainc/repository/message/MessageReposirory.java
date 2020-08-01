package com.senlainc.repository.message;

import com.senlainc.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageReposirory {

    Optional<Message> getMessage(long id);

    long addMessage(Message message);

    void deleteMessage(long id);

    Optional<List<Long>> getIncomingUserList(long userIdTo);

    Optional<List<Message>> getAllMessages(long userIdTo, long userIdFrom);

}
