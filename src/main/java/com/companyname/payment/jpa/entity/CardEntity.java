package com.companyname.payment.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name = "cards")
public class CardEntity extends BaseEntity {
    @Column(unique = true)
    String pan;
    String expiry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

}
