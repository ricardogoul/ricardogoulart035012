CREATE TABLE artistas (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL
);

CREATE TABLE albuns (
                        id BIGSERIAL PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        artista_id BIGINT NOT NULL,

                        CONSTRAINT fk_album_artista
                            FOREIGN KEY (artista_id)
                                REFERENCES artistas (id)
);