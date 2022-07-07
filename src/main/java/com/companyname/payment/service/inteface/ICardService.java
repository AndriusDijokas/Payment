package com.companyname.payment.service.inteface;

import com.companyname.payment.jpa.entity.CardEntity;
import com.companyname.payment.jpa.entity.UserEntity;
import com.companyname.payment.model.payment.Card;

public interface ICardService {
    CardEntity createIfNotExists(UserEntity user, Card card);
}
