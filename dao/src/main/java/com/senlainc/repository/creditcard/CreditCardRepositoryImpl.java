package com.senlainc.repository.creditcard;

import com.senlainc.entity.CreditCard;
import com.senlainc.repository.comment.CommentRepositoryImpl;
import com.senlainc.repository.creditcard.CreditCardRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(CommentRepositoryImpl.class);

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCreditCard(CreditCard creditCard) {

        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(creditCard);
    }
}
