package com.senlainc.transfer.dto;

import javax.validation.constraints.NotNull;

public class CreditCardDTO {

    private long idCard;

    @NotNull
    private String cvvCode;

    @NotNull
    private String validThru;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String cardNumber;

    private Long userId;

    public CreditCardDTO(String cvvCode, String validThru, String name,String surname, String cardNumber) {
        this.cvvCode = cvvCode;
        this.validThru = validThru;
        this.name = name;
        this.surname = surname;
        this.cardNumber = cardNumber;
    }

    public CreditCardDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "idCard=" + idCard +
                ", cvvCode='" + cvvCode + '\'' +
                ", validThru='" + validThru + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}
