package com.companyname.payment.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCriteria {

    @Parameter(required = true)
    @Schema(defaultValue = "0")
    int page;
    @Schema(defaultValue = "10")
    @Parameter(required = true)
    int size;
}
