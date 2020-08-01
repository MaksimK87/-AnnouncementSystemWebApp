package com.senlainc.announcementcategory;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.AnnouncementCategory;
import com.senlainc.repository.announcementcategory.AnnouncementCategoryRepository;
import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.transfer.dto.AnnouncementCategoryDTO;
import com.senlainc.transfer.dto.AnnouncementDTO;
import com.senlainc.transfer.mapper.AnnouncementCategoryMapper;
import com.senlainc.transfer.mapper.AnnouncementMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnouncementCategoryServiceImpl implements AnnouncementCategoryService {

    private static final Logger logger = Logger.getLogger(AnnouncementCategoryServiceImpl.class);

    @Autowired
    AnnouncementCategoryRepository announcementCategoryRepository;

    @Autowired
    AnnouncementCategoryMapper announcementCategoryMapper;

    @Autowired
    AnnouncementMapper announcementMapper;


    @Override
    public List<AnnouncementCategoryDTO> getCategoryList() {

        List<AnnouncementCategory> announcementCategories;
        announcementCategories = announcementCategoryRepository.getCategoryList().orElseThrow(() ->
                new RecordNotFoundException("Announcement categories don't available"));


        return announcementCategoryMapper.toDTO(announcementCategories);
    }

    @Override
    public int addCategory(AnnouncementCategoryDTO announcementCategoryDTO) {
        AnnouncementCategory announcementCategory = announcementCategoryMapper.toEntity(announcementCategoryDTO);
        logger.debug("Add new category: " + announcementCategory);
        return announcementCategoryRepository.addCategory(announcementCategory);
    }

    @Override
    public AnnouncementCategoryDTO getCategory(int id) {
        AnnouncementCategory announcementCategory = announcementCategoryRepository.getCategory(id).orElseThrow(() ->
                new RecordNotFoundException("Category with id: " + id + "doesn't exist"));
        logger.debug("Get category by id: " + announcementCategory);
        return announcementCategoryMapper.toDTO(announcementCategory);
    }

    @Override
    public void setCategory(AnnouncementDTO announcementDTO, AnnouncementCategoryDTO announcementCategoryDTO) {
        if (announcementDTO != null || announcementCategoryDTO != null) {
            Announcement announcement = announcementMapper.toEntity(announcementDTO);
            AnnouncementCategory announcementCategory = announcementCategoryMapper.toEntity(announcementCategoryDTO);
            announcement.setAnnouncementCategory(announcementCategory);
        }
    }

}
