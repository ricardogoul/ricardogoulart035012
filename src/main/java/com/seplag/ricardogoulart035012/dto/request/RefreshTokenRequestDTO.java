package com.seplag.ricardogoulart035012.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestDTO {

    @NotBlank(message = "O token não pode estar vazio")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }
}
