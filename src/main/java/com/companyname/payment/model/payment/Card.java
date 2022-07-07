package com.companyname.payment.model.payment;

import com.companyname.payment.controller.validation.Luhn;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record Card(
        @Size(max = 16, min = 16)
        @NotNull
        @NotBlank
        @Luhn
        String pan,
        @NotNull
        @NotBlank
        @Pattern(regexp = "(?:0[1-9]|1[0-2])/[0-9]{2}", message = "invalid format") //Todo can be validate on time.
        String expiry,
        @NotNull
        @NotBlank
        String ccv
) {
}
