CREATE TABLE exame (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    tipo VARCHAR (50) NOT NULL,
    data_exame DATE NOT NULL,
    observacao VARCHAR(100),
    codigo_especialidade BIGINT(20)NOT NULL,
    codigo_paciente BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_especialidade) REFERENCES especialidade (codigo),
    FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
    
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exame 1', 'RAIOX', '2017-06-10', 'Exame Feito com sucesso', 1, 1);

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exame 1', 'RAIOX', '2016-01-01', 'Exame Feito com sucesso', 1, 1);


INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exame 1', 'RAIOX', '2015-01-01', 'Exame Feito com sucesso', 1, 1);

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Emergencia', 'RAIOX', '2018-10-18', 'Exame Feito com sucesso', 2, 2);

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exam4', 'RAIOX', '2018-10-18', 'Exame Feito com sucesso', 2, 2);

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exam5', 'RAIOX', '2018-10-18', 'Exame Feito com sucesso', 2, 2);

INSERT INTO exame (nome, tipo, data_exame, observacao, codigo_especialidade, codigo_paciente) 
VALUES ('Exam6', 'RAIOX', '2018-10-18', 'Exame Feito com sucesso', 3, 3);
