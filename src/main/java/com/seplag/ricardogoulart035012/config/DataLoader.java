package com.seplag.ricardogoulart035012.config;

import com.seplag.ricardogoulart035012.domain.model.Usuario;
import com.seplag.ricardogoulart035012.infra.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregaUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (usuarioRepository.count() == 0) {

                Usuario usuario = new Usuario();
                usuario.setUsername("admin");
                usuario.setPassword(passwordEncoder.encode("admin123"));

                usuarioRepository.save(usuario);

                System.out.println("✔ Usuário admin criado com sucesso");
            }
        };
    }
}
