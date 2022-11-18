package com.example.company.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @NotNull(message = "Ko'cha nomi kiritilishi kerak")
    private String street;
    @NotNull(message = "Uy raqami kiritilishi kerak")
    private String homeNumber;
}
