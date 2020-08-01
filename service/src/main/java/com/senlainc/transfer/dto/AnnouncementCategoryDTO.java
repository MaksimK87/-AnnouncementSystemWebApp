package com.senlainc.transfer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class AnnouncementCategoryDTO {

    private int idCategory;

    @NotNull
    @Pattern(regexp = "^[а-яёА-ЯЁ\\s]+", message = "Only letters should be in category")
    private String category;

    private List<AnnouncementDTO> announcementList;

    public AnnouncementCategoryDTO() {
    }

    public AnnouncementCategoryDTO(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "AnnouncementCategoryDTO{" +
                "idCategory=" + idCategory +
                ", category='" + category + '\'' +
                '}';
    }
}
