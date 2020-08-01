package com.senlainc.transfer.mapper;

import com.senlainc.entity.CreditCard;
import com.senlainc.entity.User;
import com.senlainc.transfer.dto.CreditCardDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-21T16:55:52+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_152-release (JetBrains s.r.o)"
)
@Component
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCardDTO toDTO(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setUserId( creditCardUserId( creditCard ) );
        creditCardDTO.setIdCard( creditCard.getIdCard() );
        creditCardDTO.setCvvCode( creditCard.getCvvCode() );
        creditCardDTO.setValidThru( creditCard.getValidThru() );
        creditCardDTO.setName( creditCard.getName() );
        creditCardDTO.setSurname( creditCard.getSurname() );
        creditCardDTO.setCardNumber( creditCard.getCardNumber() );

        return creditCardDTO;
    }

    @Override
    public CreditCard toEntity(CreditCardDTO creditCardDTO) {
        if ( creditCardDTO == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setUser( creditCardDTOToUser( creditCardDTO ) );
        creditCard.setIdCard( creditCardDTO.getIdCard() );
        creditCard.setCvvCode( creditCardDTO.getCvvCode() );
        creditCard.setValidThru( creditCardDTO.getValidThru() );
        creditCard.setName( creditCardDTO.getName() );
        creditCard.setSurname( creditCardDTO.getSurname() );
        creditCard.setCardNumber( creditCardDTO.getCardNumber() );

        return creditCard;
    }

    @Override
    public List<CreditCard> toEntity(List<CreditCardDTO> creditCards) {
        if ( creditCards == null ) {
            return null;
        }

        List<CreditCard> list = new ArrayList<CreditCard>( creditCards.size() );
        for ( CreditCardDTO creditCardDTO : creditCards ) {
            list.add( toEntity( creditCardDTO ) );
        }

        return list;
    }

    @Override
    public List<CreditCardDTO> toDTO(List<CreditCard> creditCards) {
        if ( creditCards == null ) {
            return null;
        }

        List<CreditCardDTO> list = new ArrayList<CreditCardDTO>( creditCards.size() );
        for ( CreditCard creditCard : creditCards ) {
            list.add( toDTO( creditCard ) );
        }

        return list;
    }

    private Long creditCardUserId(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }
        User user = creditCard.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User creditCardDTOToUser(CreditCardDTO creditCardDTO) {
        if ( creditCardDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( creditCardDTO.getUserId() );

        return user;
    }
}
