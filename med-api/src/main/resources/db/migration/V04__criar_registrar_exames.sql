CREATE TABLE exame (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    tipoExame VARCHAR (30) NOT NULL,
    data DATE
)ENGINE=INNODB DEFAULT CHARSET=utf8;


Insert INTO  medapi.exame (nome,tipoExame,data) VALUES ('EXAME 1','TOXICO', '2010/06/18');