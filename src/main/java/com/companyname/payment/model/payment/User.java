package com.companyname.payment.model.payment;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record User(
        @NotBlank
        @NotNull
        String name,
        @Email
        String email
) {

}
