CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@medapi.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'Brun Silva', 'bruno@medapi.com', '$2a$10$PuxHcCyA7D5Uuwu0/o0aCOqMSprP5NIZDljMW5qDECyY/pZS6OR1q');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_ESPECIALIDADE');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_ESPECIALIDADE');

INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CADASTRAR_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_REMOVER_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_PESQUISAR_PACIENTE');

INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_CADASTRAR_EXAME');
INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_REMOVER_EXAME');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_PESQUISAR_EXAME');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);

-- Bruno
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 8);