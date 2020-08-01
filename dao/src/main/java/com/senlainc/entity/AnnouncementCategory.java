package com.senlainc.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "announcement_category")
public class AnnouncementCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannouncement_category")
    private int idCategory;

    @Column(name = "category")
    private String category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcementCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Announcement> announcementList;

    public AnnouncementCategory(String category) {
        this.category = category;
    }

    public AnnouncementCategory() {

    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    @Override
    public String toString() {
        return "AnnouncementCategory{" +
                "idCategory=" + idCategory +
                ", category='" + category + '\'' +
                '}';
    }
}

