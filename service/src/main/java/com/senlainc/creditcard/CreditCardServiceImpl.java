package com.senlainc.creditcard;

import com.senlainc.entity.CreditCard;
import com.senlainc.repository.creditcard.CreditCardRepository;
import com.senlainc.transfer.dto.CreditCardDTO;
import com.senlainc.transfer.mapper.CreditCardMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger logger = Logger.getLogger(CreditCardServiceImpl.class);

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardMapper creditCardMapper;

    @Override
    public void addCreditCard(CreditCardDTO creditCardDTO) {
        CreditCard creditCard;

        if (creditCardDTO != null) {
            creditCard = creditCardMapper.toEntity(creditCardDTO);
            creditCardRepository.addCreditCard(creditCard);
            logger.debug("Add or update credit card: " + creditCard);
        }

    }
}
