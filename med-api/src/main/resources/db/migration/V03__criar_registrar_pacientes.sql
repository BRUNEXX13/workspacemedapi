CREATE TABLE paciente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(30) NOT NULL,
	idade BIGINT
)ENGINE=INNODB DEFAULT CHARSET=utf8;
