package com.companyname.payment.service.inteface;


import com.companyname.payment.jpa.entity.UserEntity;
import com.companyname.payment.model.payment.User;

public interface IUserService {
    void checkUserExists(String email);

    UserEntity createIfNotExists(User user);
}
