package com.senlainc.announcementcategory;

import com.senlainc.transfer.dto.AnnouncementCategoryDTO;
import com.senlainc.transfer.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementCategoryService {

    List<AnnouncementCategoryDTO> getCategoryList();

    int addCategory(AnnouncementCategoryDTO announcementCategory);

    AnnouncementCategoryDTO getCategory(int id);

    void setCategory(AnnouncementDTO announcementDTO, AnnouncementCategoryDTO announcementCategoryDTO);

}
