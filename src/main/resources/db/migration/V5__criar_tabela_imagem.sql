CREATE TABLE imagens (
                         id BIGSERIAL PRIMARY KEY,
                         nome_arquivo VARCHAR(255) NOT NULL,
                         tipo VARCHAR(100) NOT NULL,
                         tamanho BIGINT NOT NULL,
                         path VARCHAR(500) NOT NULL,
                         data_upload TIMESTAMP NOT NULL,
                         album_id BIGINT NOT NULL,

                         CONSTRAINT fk_imagem_album
                             FOREIGN KEY (album_id)
                                 REFERENCES albuns(id)
                                 ON DELETE CASCADE
);
