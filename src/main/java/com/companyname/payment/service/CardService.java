package com.companyname.payment.service;

import com.companyname.payment.jpa.entity.CardEntity;
import com.companyname.payment.jpa.entity.UserEntity;
import com.companyname.payment.jpa.repository.CardRepository;
import com.companyname.payment.model.payment.Card;
import com.companyname.payment.service.inteface.ICardService;
import com.companyname.payment.utils.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    final CardRepository cardRepository;
    @Value("secret-key")
    String secretKey;
    @Value("secret-salt")
    String secretSalt;

    private final static int CHUNK_SIZE = 12;


    //Todo Pan encryption
    @Override
    public CardEntity createIfNotExists(UserEntity user, Card card) {
        return cardRepository.findByPan(card.pan()).orElseGet(() ->
                cardRepository.save(CardEntity.builder()
                        .expiry(encrypt(card.expiry()))
                        .pan(card.pan())
                        .user(user)
                        .build())

        );
    }

    private String encryptPan(String pan) {
        final String[] panChunk = pan.split("(?<=\\G.{" + CHUNK_SIZE + "})");
        final var panFirst = panChunk[0];
        final var panSecond = panChunk[1];
        return encrypt(panFirst) + panSecond;
    }

    private String decryptPan(String pan){
        final var panFirst = pan.substring(0, pan.length() - 4);
        final var panSecond = pan.substring(pan.length() - 4,pan.length());
        return decrypt(panFirst)+panSecond;
    }

    private String encrypt(String var0) {
        return AES256.encrypt(var0, secretKey, secretSalt);
    }

    private String decrypt(String var0) {
        try {
            return AES256.decrypt(var0, secretKey, secretSalt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
