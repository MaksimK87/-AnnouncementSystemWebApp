package com.senlainc.repository.message;

import com.senlainc.entity.Message;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageReposirory {

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(MessageRepositoryImpl.class);

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Message> getMessage(long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.of(session.find(Message.class, id));
    }

    @Override
    public long addMessage(Message message) {

        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(message);
    }

    @Override
    public void deleteMessage(long id) {

        Session session = sessionFactory.getCurrentSession();
        Message message = session.load(Message.class, id);
        logger.debug("delete message by id: " + message);
        if (message != null) {
            session.delete(message);
            session.flush();
        }
    }

    @Override
    public Optional<List<Long>> getIncomingUserList(long userIdTo) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("Select m.userIdFrom from Message m where m.user.id=:userIdTo");
        query.setParameter("userIdTo", userIdTo);

        List<Long> allList = query.list();
        logger.debug("List of users id who wrote message: " + allList + " for: " + userIdTo);

        return Optional.of(allList.stream().distinct().collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Message>> getAllMessages(long userIdTo, long userIdFrom) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Message m where m.userIdFrom=:userIdFrom and m.user.id=:userIdTo" +
                " order by m.publicationDate desc");
        query.setParameter("userIdTo", userIdTo);
        query.setParameter("userIdFrom", userIdFrom);

        List<Message> allMessage = query.list();

        logger.debug("all messages for userId : " + userIdTo + " >>> " + allMessage + "from user id: " + userIdFrom);

        return Optional.of(allMessage);
    }

}
