package com.senlainc.entity;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcredit_card")
    private long idCard;

    @Column(name = "cvv_code")
    private String cvvCode;

    @Column(name = "valid_thru")
    private String validThru;

    @Column(name = "owner_name")
    private String name;

    @Column(name = "owner_surname")
    private String surname;

    @Column(name = "card_number")
    private String cardNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard(String cvvCode, String validThru, String name, String surname, String cardNumber) {
        this.cvvCode = cvvCode;
        this.validThru = validThru;
        this.name = name;
        this.surname = surname;
        this.cardNumber = cardNumber;
    }

    public CreditCard() {
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getValidThru() {
        return validThru;
    }

    public void setValidThru(String validThru) {
        this.validThru = validThru;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "idCard=" + idCard +
                ", cvvCode='" + cvvCode + '\'' +
                ", validThru='" + validThru + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}