CREATE TABLE medico (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	crm VARCHAR(30) NOT NULL,
	descricao VARCHAR(50) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

