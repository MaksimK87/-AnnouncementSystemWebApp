package com.senlainc.transfer.dto;

import com.senlainc.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long id;

    @Size(min = 3, message = "name must be more than 3 symbols")
    private String username;

    @Size(min = 2, message = "password must be more than 3 symbols")
    private String password;

    @Size(min = 2, message = "password must be more than 3 symbols")
    private String passwordConfirm;

    @Email(message = "enter correct email!")
    private String email;

    @NotNull(message = "is required")
    private String region;

    private float rating;

    private String city;

    @NotNull
    @Pattern(regexp = "^((\\+375))?(\\(?\\d{9}\\)?)$", message = "enter phone number in format +375(your phone number!)!")
    private String phoneNumber;

    private Set<Role> roles;

    private List<CreditCardDTO> creditCards;

    private List<MessageDTO> messages;

    private List<AnnouncementDTO> announcements;

    private List<CommentDTO> comments;

    public UserDTO(String username, String password, String passwordConfirm,
                   String email, String region, String city, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.region = region;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<CreditCardDTO> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardDTO> creditCards) {
        this.creditCards = creditCards;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public List<AnnouncementDTO> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementDTO> announcements) {
        this.announcements = announcements;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", email='" + email + '\'' +
                ", region='" + region + '\'' +
                ", rating=" + rating +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles=" + roles +
                ", creditCards=" + creditCards +
                ", messages=" + messages +
                ", announcements=" + announcements +
                ", comments=" + comments +
                '}';
    }
}
