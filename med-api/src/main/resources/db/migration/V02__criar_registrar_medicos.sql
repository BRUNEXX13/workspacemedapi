CREATE TABLE medico (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	crm VARCHAR(30) NOT NULL,
	descricao VARCHAR(50) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO medico (nome, crm, descricao) values ('Bruno', '403-SP', 'Ginocologista');
INSERT INTO medico (nome, crm, descricao) values ('Bruno2', '40323-SP', 'Dermato');
INSERT INTO medico (nome, crm, descricao) values ('Caio', '1233-SP', 'Cirurgiao');
