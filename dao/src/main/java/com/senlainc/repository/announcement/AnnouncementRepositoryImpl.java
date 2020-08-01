package com.senlainc.repository.announcement;

import com.senlainc.entity.Announcement;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementRepositoryImpl implements AnnouncementRepository {

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(AnnouncementRepositoryImpl.class);

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<List<Announcement>> getAnnouncementsByUserRating() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement a where a.activeStatus=true order by a.user.rating desc");
        return Optional.ofNullable(query.list());
    }

    @Override
    public Optional<List<Announcement>> getListByKeyword(String key) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement a where a.header like concat('%',:key,'%') and " +
                "a.activeStatus=true order by a.creationDate desc");
        query.setParameter("key", key);
        return Optional.ofNullable(query.list());
    }

    @Override
    public Optional<List<Announcement>> getListByCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement a where a.announcementCategory.idCategory=:id " +
                "order by a.creationDate desc");
        query.setParameter("id", id);
        return Optional.ofNullable(query.list());
    }

    @Override
    public Long saveAnnouncement(Announcement announcement) {

        Session session = sessionFactory.getCurrentSession();
        logger.debug("Save announcement >>>> " + announcement);
        return (Long) session.save(announcement);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {

        logger.debug("Update announcement: " + announcement);

        Session session = sessionFactory.getCurrentSession();
        session.update(announcement);
    }

    @Override
    public void promoteAnnouncement(Announcement announcement) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update Announcement set topStatus=:isTop where idAnnouncement=:id");
        query.setParameter("id", announcement.getIdAnnouncement());
        query.setParameter("isTop", announcement.isTopStatus());
        query.executeUpdate();

    }

    @Override
    public void closeAnnouncement(Announcement announcement) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update Announcement set activeStatus=:status where idAnnouncement=:id");
        query.setParameter("id", announcement.getIdAnnouncement());
        query.setParameter("status", announcement.isActiveStatus());
        query.executeUpdate();

    }

    @Override
    public void deleteAnnouncement(long id) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = session.get(Announcement.class, id);

        if (announcement != null) {
            logger.debug("Delete announcement:" + announcement);
            session.delete(announcement);
        }
    }

    @Override
    public Optional<List<Announcement>> getAllNonTopAnnouncement() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.creationDate desc");//from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.creationDate desc"
        query.setParameter("activeStatus", true);
        query.setParameter("topStatus", false);
        List<Announcement> announcements = query.list();
        logger.debug("all non top announcements >>>> : " + announcements);
        return Optional.of(announcements);
    }

    @Override
    public Optional<List<Announcement>> getAllTopAnnouncement() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Announcement a where a.activeStatus=:activeStatus and a.topStatus=:topStatus order by a.paymentDate desc");
        query.setParameter("activeStatus", true);
        query.setParameter("topStatus", true);
        List<Announcement> announcements = query.list();

        logger.debug("all top announcements: " + announcements);

        return Optional.of(announcements);
    }

    @Override
    public Optional<Announcement> getAnnouncement(long id) {

        Session session = sessionFactory.getCurrentSession();

        Announcement announcement = session.get(Announcement.class, id);

        logger.debug("Get announcement by id: " + id + " " + " " + announcement);

        return Optional.ofNullable(announcement);
    }

    @Override
    public Optional<List<Announcement>> getAnnouncementHistory(long idUser) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select a from Announcement a where a.user.id = :id order by a.creationDate desc"); //from Announcement where user=:id order by creationDate desc
        query.setParameter("id", idUser);
        List<Announcement> announcements = query.list();

        logger.debug("Get announcement history : " + announcements);

        return Optional.ofNullable(announcements);
    }
}
