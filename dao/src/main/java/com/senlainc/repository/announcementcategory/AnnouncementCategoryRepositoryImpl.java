package com.senlainc.repository.announcementcategory;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.AnnouncementCategory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementCategoryRepositoryImpl implements AnnouncementCategoryRepository {

    private static final Logger logger = Logger.getLogger(AnnouncementCategoryRepositoryImpl.class);

    private static SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<List<AnnouncementCategory>> getCategoryList() {

        Session session = sessionFactory.getCurrentSession();
        List<AnnouncementCategory> announcementCategories;
        Query query = session.createQuery("select a from AnnouncementCategory a", AnnouncementCategory.class);
        announcementCategories = query.getResultList();
        logger.debug("--->>> Get from database announcement categories: "+announcementCategories);
        return Optional.of(announcementCategories);
    }

    @Override
    public int addCategory(AnnouncementCategory announcementCategory) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(announcementCategory);

    }

    @Override
    public Optional<AnnouncementCategory> getCategory(int id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.of(session.find(AnnouncementCategory.class,id));
    }

    @Override
    public void setCategory(Announcement announcement, AnnouncementCategory category) {

    }
}
