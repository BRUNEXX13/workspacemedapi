CREATE TABLE especialidade (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

Insert INTO  especialidade (nome) VALUES ('GINOCOLOGISTA');

Insert INTO  especialidade (nome) VALUES ('DERMATOLOGISTA');

Insert INTO  especialidade (nome) VALUES ('ESPECIALISTA');
