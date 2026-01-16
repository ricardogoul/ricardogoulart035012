CREATE TABLE refresh_token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(500) NOT NULL UNIQUE,
    usuario_id BIGINT NOT NULL,
    expiracao TIMESTAMP NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refresh_usuario
       FOREIGN KEY (usuario_id)
           REFERENCES usuario(id)
           ON DELETE CASCADE
);