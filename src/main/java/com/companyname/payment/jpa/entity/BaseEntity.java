package com.companyname.payment.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    @Column(unique = true)
    private String uuid;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
