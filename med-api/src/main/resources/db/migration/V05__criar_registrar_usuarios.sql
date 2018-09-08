CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    senha VARCHAR (30) NOT NULL
    
)ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT into usuario (nome, senha) values ('Bruno', 123);
INSERT into usuario (nome, senha) values ('Caio', 123);
INSERT into usuario (nome, senha) values ('Luana', 123)



