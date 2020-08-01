package com.senlainc.repository.user;

import com.senlainc.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from User where username=:nameParam");
        query.setParameter("nameParam", username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }


    @Override
    public Optional<User> findById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User userfromDb = session.find(User.class, userId);
        Optional<User> user = Optional.ofNullable(userfromDb);
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User order by userName");
        List<User> users = query.list();
        return users;
    }

    @Override
    public long save(User user) {
        Session session = sessionFactory.getCurrentSession();
        long id=(long) session.save(user);
        logger.debug("Saved user with id: "+id);
        return id;
    }

    public void update(User user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        logger.debug("update user :" +user);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
