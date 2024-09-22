CREATE TABLE USERS (
        ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255) NOT NULL,                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
        EMAIL VARCHAR(255) NOT NULL,
        PASSWORD VARCHAR(64) NOT NULL,
        CPF_CNPJ VARCHAR(24) NOT NULL,
        CEP VARCHAR(24) NOT NULL,
        IS_ACTIVE BOOLEAN DEFAULT TRUE
);

CREATE TABLE PET (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USER_ID INTEGER REFERENCES USERS(ID) ON UPDATE CASCADE ON DELETE CASCADE,
    NAME VARCHAR(255),
    DATA_ADOCAO DATE,
    RACA VARCHAR(50),
    SEXO INTEGER, --Visando eficiencia será salvo em int p/ tipo
    PORTE INTEGER, --Visando eficiencia será salvo em int p/ tipo
    TEMPERAMENTO INTEGER, --Visando eficiencia será salvo em int por tipo
    COR_PELO INTEGER,
    DATA_NASC DATE
);

CREATE TABLE QRCODE (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PET_ID INTEGER REFERENCES PET(ID) UNIQUE,
    USER_ID INTEGER REFERENCES USERS(ID),
    UUID VARCHAR(36),
    IMG_QR VARCHAR(MAX), --  quando no postgre IMG_QR BYTEA,
    ACTIVATION_DATE DATETIME,
    IS_ACTIVE BOOLEAN
);
