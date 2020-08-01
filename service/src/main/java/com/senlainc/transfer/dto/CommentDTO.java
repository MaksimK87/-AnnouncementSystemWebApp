package com.senlainc.transfer.dto;

import javax.validation.constraints.NotNull;

public class CommentDTO {

    private long idComment;

    @NotNull
    private String commentText;

    private String publicationDate;

    private long announcementId;

    private long userId;

    public CommentDTO() {
    }

    public CommentDTO(String commentText) {
        this.commentText = commentText;
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "idComment=" + idComment +
                ", commentText='" + commentText + '\'' +
                ", publicationDate=" + publicationDate +
                ", announcementId=" + announcementId +
                ", userId=" + userId +
                '}';
    }
}
