package com.senlainc.controller;

import com.senlainc.announcement.AnnouncementService;
import com.senlainc.creditcard.CreditCardService;
import com.senlainc.transfer.dto.AnnouncementDTO;
import com.senlainc.transfer.dto.CreditCardDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AnnouncementController {
    private static final Logger logger = Logger.getLogger(AnnouncementController.class);

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/announcements/{id}")
    ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable long id) {
        logger.info("Get announcement id: " + id);
        return ResponseEntity.ok(announcementService.getAnnouncement(id));
    }

    @GetMapping("/announcements/{id}/history")
    ResponseEntity<Collection<AnnouncementDTO>> getAnnouncementsHistory(@PathVariable long id) {

        List<AnnouncementDTO> announcements = announcementService.getAnnouncementHistory(id);
        logger.info("Announcement history for userId: " + id + " >>> " + announcements);
        return new ResponseEntity(announcements, HttpStatus.OK);
    }

    @GetMapping("/announcements")
    ResponseEntity<List<AnnouncementDTO>> getAnnouncementList() {
        List<AnnouncementDTO> list = new LinkedList<>();
        list.addAll(announcementService.getAllTopAnnouncement());
        list.addAll(announcementService.getAllNonTopAnnouncement());
        logger.info("Get all announcements >>> " + list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/announcements/find/{keyword}")
    ResponseEntity<List<AnnouncementDTO>> getListByKeyword(@PathVariable String keyword) {

        List<AnnouncementDTO> announcementDTOS = announcementService.getListByKeyword(keyword);
        logger.info("Get announcements by key word: "+keyword);
        return ResponseEntity.ok(announcementDTOS);
    }

    @GetMapping("/announcements/categories/{id}")
    ResponseEntity<List<AnnouncementDTO>> getAnnouncementsByCategory(@PathVariable int id) {

        List<AnnouncementDTO> announcementDTOS = announcementService.getListByCategory(id);
        logger.info("Get announcement list by category id: "+id+" "+announcementDTOS);
        return ResponseEntity.ok(announcementDTOS);
    }

    @GetMapping("/announcements/rating")
    ResponseEntity<List<AnnouncementDTO>> getAnnouncementsByUserRating() {

        List<AnnouncementDTO> announcementDTOS = announcementService.getAnnouncementsByUserRating();
        logger.info("Get announcement list by user rating: "+announcementDTOS);
        return ResponseEntity.ok(announcementDTOS);
    }

    @PostMapping("/announcements")
    ResponseEntity<AnnouncementDTO> saveAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) {

        logger.info("Save new announcement: " + announcementDTO);

        AnnouncementDTO announcement;
        announcement = announcementService.getAnnouncement(announcementService.saveAnnouncement(announcementDTO));
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(announcement.getIdAnnouncement()).toUri();

        return ResponseEntity.created(uri).body(announcement);
    }

    @PutMapping("/announcements")
    ResponseEntity<AnnouncementDTO> updateAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) {

        announcementService.updateAnnouncement(announcementDTO);
        AnnouncementDTO announcement = announcementService.getAnnouncement(announcementDTO.getIdAnnouncement());
        logger.info("Updated announcement: " + announcement);

        return new ResponseEntity(announcement, HttpStatus.OK);
    }

    @DeleteMapping("/announcements/{id}")
    ResponseEntity deleteAnnouncement(@PathVariable long id) {
        announcementService.deleteAnnouncement(id);
        logger.info("Delete announcement by id: " + id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/announcements/{id}/close")
    ResponseEntity closeAnnouncement(@PathVariable long id) {
        announcementService.closeAnnouncement(id);
        logger.info("Announcement with id:" + id + " is closed");
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/announcements/{id}/promote")
    ResponseEntity<AnnouncementDTO> promoteAnnouncement(@PathVariable long id, @Valid @RequestBody CreditCardDTO creditCardDTO) {
        announcementService.promoteAnnouncement(id);
        AnnouncementDTO announcementDTO = announcementService.getAnnouncement(id);
        creditCardDTO.setUserId(announcementDTO.getUserId());
        creditCardService.addCreditCard(creditCardDTO);
        logger.info("Announcement is promoted:" + announcementService);
        return new ResponseEntity(announcementDTO, HttpStatus.OK);
    }

}
