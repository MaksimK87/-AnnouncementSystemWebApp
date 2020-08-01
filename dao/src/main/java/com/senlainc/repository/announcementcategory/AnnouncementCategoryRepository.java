package com.senlainc.repository.announcementcategory;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.AnnouncementCategory;

import java.util.List;
import java.util.Optional;

public interface AnnouncementCategoryRepository {

    Optional<List<AnnouncementCategory>> getCategoryList();

    int addCategory(AnnouncementCategory announcementCategory);

    Optional<AnnouncementCategory> getCategory(int id);

    void setCategory(Announcement announcement, AnnouncementCategory category);


}
