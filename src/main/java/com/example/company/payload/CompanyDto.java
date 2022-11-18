package com.example.company.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CompanyDto {
    @NotNull(message = "Satr bo'sh bo'lmasili kerak")
    private String corpName;
    @NotNull(message = "Satr bo'sh bo'lmasili kerak")
    private String directorName;
    @NotNull(message = "Satr bo'sh bo'lmasili kerak")
    private Integer addressId;
}
