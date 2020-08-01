package com.senlainc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmessage")
    private long idMessage;

    @Column(name = "message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="publication_date")
    private Date publicationDate;

    @Column(name = "user_id_from")
    private Long userIdFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id_to")
    private User user;


    public Message() {
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", message='" + message + '\'' +
                ", publicationDate=" + publicationDate +
                ", userIdFrom=" + userIdFrom +
                '}';
    }
}

