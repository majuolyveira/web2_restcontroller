CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(255),
    numero INTEGER,
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(255),
    uf VARCHAR(2),
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
