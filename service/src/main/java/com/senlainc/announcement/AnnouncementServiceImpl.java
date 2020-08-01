package com.senlainc.announcement;

import com.senlainc.entity.Announcement;
import com.senlainc.repository.announcement.AnnouncementRepository;
import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.transfer.dto.AnnouncementDTO;
import com.senlainc.transfer.mapper.AnnouncementMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {


    private static final Logger logger = Logger.getLogger(AnnouncementServiceImpl.class);

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementMapper announcementMapper;


    @Override
    public List<AnnouncementDTO> getAnnouncementsByUserRating() {
        List<Announcement> announcements = announcementRepository.getAnnouncementsByUserRating().orElseThrow(() ->
                new RecordNotFoundException("announcements by user rating not found"));
        return announcementMapper.toDTO(announcements);
    }


    @Override
    public List<AnnouncementDTO> getListByKeyword(String key) {

        String word = key.trim();
        List<Announcement> announcements = announcementRepository.getListByKeyword(word).orElseThrow(() ->
                new RecordNotFoundException("Nothing was found by keyword: " + word));
        logger.debug("Announcements found by keyword: " + key + " " + announcements);
        return announcementMapper.toDTO(announcements);
    }


    @Override
    public List<AnnouncementDTO> getListByCategory(int id) {
        List<Announcement> announcements = announcementRepository.getListByCategory(id).orElseThrow(() ->
                new RecordNotFoundException("announcements with id category: " + id + " don't exist"));
        logger.debug("Get announcements by category id:" + id + " " + announcements);
        return announcementMapper.toDTO(announcements);
    }


    @Override
    public Long saveAnnouncement(AnnouncementDTO announcementDTO) {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        announcementDTO.setCreationDate(dateFormat.format(calendar.getTime()));

        logger.debug("DTO announcement: " + announcementDTO);

        Announcement announcement = announcementMapper.toEntity(announcementDTO);

        announcement.setCreationDate(Calendar.getInstance().getTime());
        announcement.setActiveStatus(true);

        logger.debug("Announcement from DTO convertation: " + announcement);
        return announcementRepository.saveAnnouncement(announcement);
    }


    @Override
    public void updateAnnouncement(AnnouncementDTO announcementDTO) {

        logger.debug("DTO announcement: " + announcementDTO);

        Announcement announcement = announcementMapper.toEntity(announcementDTO);

        logger.debug("Announcement from DTO convertation: " + announcement);

        announcementRepository.updateAnnouncement(announcement);
    }


    @Override
    public void promoteAnnouncement(long id) {

        Announcement announcement = announcementRepository.getAnnouncement(id).orElseThrow(() ->
                new RecordNotFoundException("Announcement with id:" + id + " doesn't exist"));
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        announcement.setPaymentDate((date));
        announcement.setTopStatus(true);
        logger.debug("Announcement was promoted: " + announcement);

        announcementRepository.promoteAnnouncement(announcement);
    }


    @Override
    public void closeAnnouncement(long id) {

        Announcement announcement;

        announcement = announcementRepository.getAnnouncement(id).orElseThrow(() ->
                new RecordNotFoundException("Announcement with id:" + id + " doesn't exist"));
        announcement.setActiveStatus(false);

        announcementRepository.closeAnnouncement(announcement);
        logger.debug("Announcement is closed: " + announcement);

    }


    @Override
    public List<AnnouncementDTO> getAllNonTopAnnouncement() {

        List<Announcement> announcements;
        announcements = announcementRepository.getAllNonTopAnnouncement().orElseGet(() -> Collections.singletonList(new Announcement()));

        logger.debug("Non top announcements: " + announcements);

        return announcementMapper.toDTO(announcements);
    }


    @Override
    public List<AnnouncementDTO> getAllTopAnnouncement() {

        List<Announcement> topAnnouncements;
        topAnnouncements = announcementRepository.getAllTopAnnouncement().orElseGet(() -> Collections.singletonList(new Announcement()));

        if (isDateOfTopExpired(topAnnouncements)) {
            topAnnouncements = announcementRepository.getAllTopAnnouncement().orElseThrow(() ->
                    new RecordNotFoundException("All top announcements don't available for this user"));
        }
        logger.debug("Top announcements: " + topAnnouncements);
        return announcementMapper.toDTO(topAnnouncements);
    }


    @Override
    public AnnouncementDTO getAnnouncement(long id) {

        Announcement announcement;

        announcement = announcementRepository.getAnnouncement(id).orElseThrow(() ->
                new RecordNotFoundException("announcement with id: " + id + " doesn't exist"));
        logger.debug("Announcement for id: " + announcement);
        return announcementMapper.toDto(announcement);
    }


    @Override
    public List<AnnouncementDTO> getAnnouncementHistory(long idUser) {

        List<Announcement> announcementHistory;

        announcementHistory = announcementRepository.getAnnouncementHistory(idUser).orElseThrow(() ->
                new RecordNotFoundException("Announcement history doesn't available for this userId: " + idUser));
        logger.debug("Announcement history for user id: " + idUser + " >>> " + announcementHistory);
        return announcementMapper.toDTO(announcementHistory);
    }


    @Override
    public void deleteAnnouncement(long id) {
        logger.debug("Delete announcement by id:" + id);
        announcementRepository.deleteAnnouncement(id);
    }


    private boolean isDateOfTopExpired(List<Announcement> topAnnouncements) {

        int daysInTop = 7;
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime paymentDurationtime;
        Boolean isNeedToUpdateAnnouncements = false;

        for (Announcement announcement : topAnnouncements) {

            paymentDurationtime = announcement.getPaymentDate().toInstant().
                    atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(daysInTop);

            logger.debug("Payment duration time: " + paymentDurationtime + " for announcement: " + announcement);

            if (currentTime.isAfter(paymentDurationtime)) {
                logger.debug("Current time: " + currentTime + " payment to date: " + paymentDurationtime);
                logger.debug("Announcement go out of the top list: " + announcement);
                announcement.setTopStatus(false);
                announcementRepository.promoteAnnouncement(announcement);
                isNeedToUpdateAnnouncements = true;
            }

        }
        return isNeedToUpdateAnnouncements;
    }
}
