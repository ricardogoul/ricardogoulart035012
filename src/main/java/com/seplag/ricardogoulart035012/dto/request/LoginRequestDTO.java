package com.seplag.ricardogoulart035012.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(min = 1, max = 100, message = "O nome do usuário deve ter entre 1 e 100 caracteres.")
    private String username;

    @NotBlank(message = "a senha é obrigatória.")
    @Size(min = 3, max = 10, message = "A senha deve ter entre 3 e 100 caracteres.")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
