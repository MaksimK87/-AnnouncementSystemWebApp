package com.senlainc.controller;

import com.senlainc.announcementcategory.AnnouncementCategoryService;
import com.senlainc.transfer.dto.AnnouncementCategoryDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnnouncementCategoryController {


    private static final Logger logger = Logger.getLogger(AnnouncementCategoryController.class);

    @Autowired
    AnnouncementCategoryService announcementCategoryService;

    @GetMapping("users/announcements/categories")
    ResponseEntity<List<AnnouncementCategoryDTO>> getAllCategories() {
        List<AnnouncementCategoryDTO> announcementCategories = announcementCategoryService.getCategoryList();

        return ResponseEntity.ok(announcementCategories);

    }

    @PostMapping("admin/announcements/categories")
    ResponseEntity<AnnouncementCategoryDTO> addCategory(@Valid @RequestBody AnnouncementCategoryDTO announcementCategoryDTO) {
        logger.debug("New category: >>> "+announcementCategoryDTO);
       int id=announcementCategoryService.addCategory(announcementCategoryDTO);
       AnnouncementCategoryDTO category=announcementCategoryService.getCategory(id);
       logger.info("New category: "+category );
        return ResponseEntity.ok(category);

    }
}