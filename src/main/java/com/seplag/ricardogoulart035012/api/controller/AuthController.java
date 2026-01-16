package com.seplag.ricardogoulart035012.api.controller;

import com.seplag.ricardogoulart035012.domain.service.AuthService;
import com.seplag.ricardogoulart035012.dto.request.LoginRequestDTO;
import com.seplag.ricardogoulart035012.dto.request.RefreshTokenRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.TokenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO login(@Valid @RequestBody LoginRequestDTO loginDTO){
        return authService.login(loginDTO);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO refresh(@Valid @RequestBody RefreshTokenRequestDTO refreshRequestDTO){
        return authService.refresh(refreshRequestDTO);
    }
}
