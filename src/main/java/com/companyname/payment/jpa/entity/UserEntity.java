package com.companyname.payment.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name ="users")
public class UserEntity extends BaseEntity {

    String name;
    @Column(unique = true)
    String email;

    @OneToMany(mappedBy = "user")
    Set<CardEntity> cards;
}
