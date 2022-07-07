package com.companyname.payment.jpa.repository;

import com.companyname.payment.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
