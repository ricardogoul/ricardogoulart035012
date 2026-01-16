package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.RefreshToken;
import com.seplag.ricardogoulart035012.domain.model.Usuario;
import com.seplag.ricardogoulart035012.dto.request.LoginRequestDTO;
import com.seplag.ricardogoulart035012.dto.request.RefreshTokenRequestDTO;
import com.seplag.ricardogoulart035012.dto.response.TokenResponseDTO;
import com.seplag.ricardogoulart035012.infra.repository.UsuarioRepository;
import com.seplag.ricardogoulart035012.security.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenService jwtTokenService;

    public AuthService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, RefreshTokenService refreshTokenService, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenService = refreshTokenService;
        this.jwtTokenService = jwtTokenService;
    }

    public TokenResponseDTO login(LoginRequestDTO loginDTO) {
        Authentication authentication = authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        Usuario usuario = buscarUsuario(authentication.getName());
        return gerarTokens(usuario);
    }

    public TokenResponseDTO refresh(RefreshTokenRequestDTO refreshRequestDTO) {
        RefreshToken refreshToken = refreshTokenService.validarRefreshToken(refreshRequestDTO.getRefreshToken());
        Usuario usuario = refreshToken.getUsuario();
        return gerarTokens(usuario);
    }

    private Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    private Usuario buscarUsuario(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    private TokenResponseDTO mapToResponse(String accessToken, String refreshToken){
        TokenResponseDTO response = new TokenResponseDTO();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }

    private TokenResponseDTO gerarTokens(Usuario usuario){
        String accessToken = jwtTokenService.gerarAccessToken(usuario.getUsername());
        String refreshToken = jwtTokenService.gerarRefreshToken(usuario.getUsername());
        refreshTokenService.criarRefreshToken(usuario, refreshToken);
        return mapToResponse(accessToken, refreshToken);
    }
}
