package com.senlainc.transfer.dto;

import javax.validation.constraints.NotNull;

public class MessageDTO {

    private long idMessage;

    @NotNull
    private String message;

    private String publicationDate;

    private Long userIdFrom;

    private Long userIdTo;

    public MessageDTO() {

    }

    public MessageDTO(String message) {
        this.message = message;
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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(Long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public Long getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(Long userIdTo) {
        this.userIdTo = userIdTo;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "idMessage=" + idMessage +
                ", message='" + message + '\'' +
                ", publicationDate=" + publicationDate +
                ", userIdFrom=" + userIdFrom +
                ", userIdTo=" + userIdTo +
                '}';
    }
}
