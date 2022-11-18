package com.example.company.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull(message = "To'dirilishi kerak")
    private String name;
    @NotNull(message = "To'dirilishi kerak")
    private String phoneNumber;
    @NotNull(message = "To'dirilishi kerak")
    private Integer addressId;
    @NotNull(message = "To'dirilishi kerak")
    private Integer deparmentId;
}
