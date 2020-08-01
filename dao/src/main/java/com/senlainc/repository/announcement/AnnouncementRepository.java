package com.senlainc.repository.announcement;

import com.senlainc.entity.Announcement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository {

    Optional<List<Announcement>> getAnnouncementsByUserRating();

    Optional<List<Announcement>> getListByKeyword(String key);

    Optional<List<Announcement>> getListByCategory(int id);

    Long saveAnnouncement(Announcement announcement);

    void updateAnnouncement(Announcement announcement);

    void promoteAnnouncement(Announcement announcement);

    void closeAnnouncement(Announcement announcement);

    void deleteAnnouncement(long id);

    Optional<List<Announcement>> getAllNonTopAnnouncement();

    Optional<List<Announcement>> getAllTopAnnouncement();

    Optional<Announcement> getAnnouncement(long id);

    Optional<List<Announcement>> getAnnouncementHistory(long idUser);

}
