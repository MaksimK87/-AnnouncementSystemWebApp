package com.senlainc.announcement;

import com.senlainc.transfer.dto.AnnouncementDTO;

import java.util.List;


public interface AnnouncementService {

    List<AnnouncementDTO> getAnnouncementsByUserRating();

    List<AnnouncementDTO> getListByKeyword(String key);

    List<AnnouncementDTO> getListByCategory(int id);

    Long saveAnnouncement(AnnouncementDTO announcement);

    void updateAnnouncement(AnnouncementDTO announcement);

    void promoteAnnouncement(long id);

    void closeAnnouncement(long id);

    List<AnnouncementDTO> getAllNonTopAnnouncement();

    List<AnnouncementDTO> getAllTopAnnouncement();

    AnnouncementDTO getAnnouncement(long id);

    List<AnnouncementDTO> getAnnouncementHistory(long idUser);

    void deleteAnnouncement(long id);
}
