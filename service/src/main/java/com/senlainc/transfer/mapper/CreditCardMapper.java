package com.senlainc.transfer.mapper;

import com.senlainc.entity.CreditCard;
import com.senlainc.transfer.dto.CreditCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    @Mapping(source = "user.id", target = "userId")
    CreditCardDTO toDTO(CreditCard creditCard);

    @Mapping(source = "userId", target = "user.id")
    CreditCard toEntity(CreditCardDTO creditCardDTO);

    List<CreditCard> toEntity(List<CreditCardDTO> creditCards);

    List<CreditCardDTO> toDTO(List<CreditCard> creditCards);
}
