package com.senlainc.transfer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class AnnouncementDTO {

    private long idAnnouncement;

    @NotNull(message = "must be not empty")
    private String header;

    @NotNull(message = "must be not empty")
    private String description;


    private boolean topStatus;


    private String creationDate;


    private String paymentDate;


    private boolean activeStatus;

    @Positive(message = "Enter price")
    private double itemPrice;

    private Long userId;

    private Long announcementCategoryId;

    private List<CommentDTO> comments;

    public AnnouncementDTO() {

    }

    public AnnouncementDTO(String header, String description, double itemPrice) {
        this.header = header;
        this.description = description;
        this.itemPrice = itemPrice;
    }

    public long getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(long idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTopStatus() {
        return topStatus;
    }

    public void setTopStatus(boolean topStatus) {
        this.topStatus = topStatus;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnnouncementCategoryId() {
        return announcementCategoryId;
    }

    public void setAnnouncementCategoryId(Long announcementCategoryId) {
        this.announcementCategoryId = announcementCategoryId;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "AnnouncementDTO{" +
                "idAnnouncement=" + idAnnouncement +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", topStatus=" + topStatus +
                ", creationDate=" + creationDate +
                ", paymentDate=" + paymentDate +
                ", activeStatus=" + activeStatus +
                ", itemPrice=" + itemPrice +
                ", userId=" + userId +
                ", announcementCategoryId=" + announcementCategoryId +
                ", comments=" + comments +
                '}' + "\n";
    }
}
