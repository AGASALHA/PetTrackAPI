CREATE TABLE USERS (
        USER_ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255) NOT NULL,                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
        EMAIL VARCHAR(255) NOT NULL,
        PASSWORD VARCHAR(64) NOT NULL,
        CPF_CNPJ VARCHAR(24) NOT NULL,
        CEP VARCHAR(24) NOT NULL,
        IS_ACTIVE BOOLEAN DEFAULT TRUE
);

CREATE TABLE PETS (
    PET_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USER_ID INTEGER REFERENCES USERS(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    NAME VARCHAR(255) NOT NULL,
    DATA_ADOCAO DATE NOT NULL,
    RACA VARCHAR(50) NOT NULL,
    SEXO INTEGER NOT NULL, --Visando eficiencia será salvo em int p/ tipo
    PORTE INTEGER NOT NULL, --Visando eficiencia será salvo em int p/ tipo
    TEMPERAMENTO INTEGER NOT NULL, --Visando eficiencia será salvo em int p/ tipo
    COR_PELO INTEGER NOT NULL,
    DATA_NASC DATE
);

CREATE TABLE QRCODE (
    QR_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PET_ID INTEGER REFERENCES PETS(PET_ID) UNIQUE,
    USER_ID INTEGER REFERENCES USERS(USER_ID),
    UUID VARCHAR(36),
    ACTIVATION_DATE DATETIME,
    IS_ACTIVE BOOLEAN
);
