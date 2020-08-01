package com.senlainc.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "announcement")
public class Announcement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannouncement")
    private long idAnnouncement;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "isTop")
    private boolean topStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "isActive")
    private boolean activeStatus;

    @Column(name = "price")
    private double itemPrice;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "announcement_category_id")
    private AnnouncementCategory announcementCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Comment> comments;


    public Announcement(String header, String description, boolean topStatus,
                        Date creationDate, Date paymentDate, boolean activeStatus, double itemPrice) {
        this.header = header;
        this.description = description;
        this.topStatus = topStatus;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.activeStatus = activeStatus;
        this.itemPrice = itemPrice;
    }

    public Announcement() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnnouncementCategory getAnnouncementCategory() {
        return announcementCategory;
    }

    public void setAnnouncementCategory(AnnouncementCategory announcementCategory) {
        this.announcementCategory = announcementCategory;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "idAnnouncement=" + idAnnouncement +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", topStatus=" + topStatus +
                ", creationDate=" + creationDate +
                ", paymentDate=" + paymentDate +
                ", activeStatus=" + activeStatus +
                ", itemPrice=" + itemPrice +
                '}' + "\n";
    }
}

