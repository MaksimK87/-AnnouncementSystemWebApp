package com.senlainc.repository.comment;

import com.senlainc.entity.Comment;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(CommentRepositoryImpl.class);

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Comment> addComment(Comment comment) {
        long id;
        Comment newCommnet;
        Session session = sessionFactory.getCurrentSession();
        id = (long) session.save(comment);
        newCommnet = session.find(Comment.class, id);
        return Optional.of(newCommnet);
    }

    @Override
    public Optional<List<Comment>> getComments(long announcementId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Comment c where c.announcement.idAnnouncement=:announcementId" +
                " order by c.publicationDate desc");
        query.setParameter("announcementId",announcementId);

        return Optional.of(query.list());
    }


}
