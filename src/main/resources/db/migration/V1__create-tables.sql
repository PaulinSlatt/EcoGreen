CREATE TABLE usuario_energia (
                                 id_usuario_energia BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                 nome VARCHAR(255),
                                 email VARCHAR(255) UNIQUE,
                                 senha VARCHAR(255),
                                 ativo TINYINT DEFAULT 1
);

CREATE TABLE consumo_energia (
                                 id_consumo BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                 usuario_id BIGINT NOT NULL,
                                 data DATE,
                                 consumo_kwh DECIMAL(10, 2),
                                 ativo TINYINT DEFAULT 1,
                                 tendencia VARCHAR(10),
                                 FOREIGN KEY (usuario_id) REFERENCES usuario_energia(id_usuario_energia)
);
