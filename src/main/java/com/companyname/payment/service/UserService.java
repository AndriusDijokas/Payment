package com.companyname.payment.service;


import com.companyname.payment.controller.exception.ErrorStatus;
import com.companyname.payment.jpa.entity.UserEntity;
import com.companyname.payment.jpa.repository.UserRepository;
import com.companyname.payment.model.payment.User;
import com.companyname.payment.service.inteface.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void checkUserExists(String email) {
        var exists = userRepository.existsByEmail(email);
        if (!exists) ErrorStatus.NO_USER_FOUND.throwException();
    }

    @Override
    public UserEntity createIfNotExists(User user) {
        return userRepository.findByEmail(user.email()).orElseGet(() ->
                userRepository.save(UserEntity.builder().email(user.email()).name(user.name()).build())
        );
    }


}
