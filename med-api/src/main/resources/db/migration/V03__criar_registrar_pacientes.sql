CREATE TABLE paciente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(30) NOT NULL,
	idade BIGINT,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	ativo BOOLEAN NOT NULL

)ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO paciente 
(nome, cpf, idade, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values 
('João Silva', '369799547889', 8, 'Rua do Piratas', '10', 'Morumbi', 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);


INSERT INTO paciente 
(nome, cpf, idade, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values 
('Bruno Silva', '36964400869', 12, 'Rua do Abacaxi', '10', 'Vila Olimpia', 'Brasil', '38.400-05', 'Ri de Janeiro', 'RJ', true);


INSERT INTO paciente 
(nome, cpf, idade, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values 
('Luana Silva', '123456', 14, 'Rua do Caqui', '10', 'Vila Merce', 'Brasil', '045.50-003', 'São Paulo', 'SP', true);

