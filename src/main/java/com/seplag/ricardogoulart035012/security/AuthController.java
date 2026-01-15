package com.seplag.ricardogoulart035012.security;

import com.seplag.ricardogoulart035012.dto.request.LoginRequestDTO;
import com.seplag.ricardogoulart035012.dto.request.RefreshTokenRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.TokenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenService jwtTokenService;

    public AuthController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO login(@Valid @RequestBody LoginRequestDTO loginDTO){

        if (!"admin".equals(loginDTO.getUsername())) {
            throw new RuntimeException("Login inválido");
        }

        if (!"123".equals(loginDTO.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String accessToken = jwtTokenService.gerarAccessToken(loginDTO.getUsername());
        String refreshToken = jwtTokenService.gerarRefreshToken(loginDTO.getUsername());
        return mapToResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO refresh(@Valid @RequestBody RefreshTokenRequestDTO refreshRequestDTO){
        String username = jwtTokenService.validarTokenEObterUsuario(refreshRequestDTO.getRefreshToken());
        String novoAccessToken = jwtTokenService.gerarAccessToken(username);
        String novoRefreshToken = jwtTokenService.gerarRefreshToken(username);
        return mapToResponse(novoAccessToken, novoRefreshToken);
    }

    private TokenResponseDTO mapToResponse(String accessToken, String refreshToken){
        TokenResponseDTO response = new TokenResponseDTO();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }
}
