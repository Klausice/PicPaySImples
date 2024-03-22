package com.PicPaySImplificado.dtos;

import com.PicPaySImplificado.domain.User.UserType;

import java.math.BigDecimal;

public record UserDTO(String fistName, String LastName, BigDecimal document, BigDecimal balance, String email, String password, UserType userType) {
}
