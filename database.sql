- PROJETO (PostgreSQL + JAVA)

/*
ACADEMIA============================================
    - Cadastro
        -Alunos
        -Professores
        -Atividades
        -Turma
        -Matricula (1 aluno em uma turma com um professor e atividade)
    - Relatório
        -Alunos (Por nome, matricula, idade ou aniversariante do mês)
        -Professores (Por Atividades, lembro que um professor pode estar em turmas de atividades diferentes).
        -Atividades (por turno)
        -Turmas (por nome, atividades ou professores)
        -Matriculas (por alunos, por turmas)

================================================================================================================

O banco de dados deverá ser documentado com a modelagem de dados e a lista de procedures/functions criadas

*/

====================

CREATE DATABASE ProjetoFinal_java_pg;

CREATE TABLE tab_aluno (
	id int PRIMARY KEY,
	nome varchar(20) NOT NULL,
	telefone varchar(20),
	endereco varchar(100),
	nascimento date
);

CREATE TABLE tab_professor (
    id int PRIMARY KEY,
	nome varchar(20) NOT NULL,
	cpf varchar(14) NOT NULL,
	telefone varchar(20) NOT NULL
);

CREATE TABLE tab_atividade (
    id int PRIMARY KEY,
    nome varchar(20) NOT NULL,
    turno varchar(8),
    idProfessor int NOT NULL,
    CONSTRAINT FK_Atividade_Professor
	FOREIGN KEY (idProfessor) REFERENCES tab_professor(id)
);

CREATE TABLE tab_turma (
    id int PRIMARY KEY,
    nome varchar(20) NOT NULL,
    idProfessor int NOT NULL,
    idAtividade int NOT NULL,
    CONSTRAINT FK_Turma_Professor
	FOREIGN KEY (idProfessor) REFERENCES tab_professor(id),
	CONSTRAINT FK_Turma_Atividade
	FOREIGN KEY (idAtividade) REFERENCES tab_atividade(id)
);

CREATE TABLE tab_matricula (
    id int PRIMARY KEY,
    idAluno int NOT NULL,
    idTurma int NOT NULL,
    CONSTRAINT FK_Matricula_Aluno
	FOREIGN KEY (idAluno) REFERENCES tab_aluno(id),
	CONSTRAINT FK_Matricula_Turma
	FOREIGN KEY (idTurma) REFERENCES tab_turma(id)
);

====================

-- Criando auto incremento para os "ids" 

CREATE SEQUENCE seq_idAluno
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE tab_aluno ALTER COLUMN id SET DEFAULT NEXTVAL('seq_idAluno'::regclass);

CREATE SEQUENCE seq_idProfessor
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE tab_professor ALTER COLUMN id SET DEFAULT NEXTVAL('seq_idProfessor'::regclass);

CREATE SEQUENCE seq_idAtividade
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE tab_atividade ALTER COLUMN id SET DEFAULT NEXTVAL('seq_idAtividade'::regclass);

CREATE SEQUENCE seq_idTurma
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE tab_turma ALTER COLUMN id SET DEFAULT NEXTVAL('seq_idTurma'::regclass);

CREATE SEQUENCE seq_idMatricula
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE tab_matricula ALTER COLUMN id SET DEFAULT NEXTVAL('seq_idMatricula'::regclass);

====================

-- Criando functions e/ou procedures

-- Inserir novo aluno
CREATE OR REPLACE FUNCTION novoAluno (varchar(20), varchar(20), varchar(20), date)
RETURNS void AS $$

	INSERT INTO tab_aluno (nome, telefone, endereco, nascimento)
	VALUES ($1, $2, $3, $4);

	SELECT CURRVAL('seq_idAluno');

$$
LANGUAGE 'sql';

-- Inserir novo professor
CREATE OR REPLACE FUNCTION novoProfessor (varchar(20), varchar(20), varchar(20))
RETURNS void AS $$

	INSERT INTO tab_professor (nome, cpf, telefone)
	VALUES ($1, $2, $3);

	SELECT CURRVAL('seq_idProfessor');

$$
LANGUAGE 'sql';

-- Inserir nova atividade
CREATE OR REPLACE FUNCTION novaAtividade (varchar(20), varchar(20), varchar(20))
RETURNS void AS $$

	INSERT INTO tab_atividade (nome, turno, idProfessor)
	VALUES ($1, $2, (SELECT id FROM tab_professor WHERE nome LIKE '%' || $3 || '%'));

	SELECT CURRVAL('seq_idAtividade');

$$
LANGUAGE 'sql';

-- Inserir nova turma
CREATE OR REPLACE FUNCTION novaTurma (varchar(20), varchar(20), varchar(20))
RETURNS void AS $$

	INSERT INTO tab_turma (nome, idProfessor, idAtividade)
	VALUES (
				$1,
				(SELECT id FROM tab_professor WHERE nome LIKE '%' || $2 || '%'),
				(SELECT id FROM tab_atividade WHERE nome LIKE '%' || $3 || '%')
			);

	SELECT CURRVAL('seq_idTurma');

$$
LANGUAGE 'sql';

-- Inserir nova matrícula
CREATE OR REPLACE FUNCTION novaMatricula (varchar(20), varchar(20))
RETURNS void AS $$

	INSERT INTO tab_matricula (idAluno, idTurma)
	VALUES (
				(SELECT id FROM tab_aluno WHERE nome LIKE '%' || $1 || '%'),
		   		(SELECT id FROM tab_turma WHERE nome LIKE '%' || $2 || '%')
			);

	SELECT CURRVAL('seq_idMatricula');

$$
LANGUAGE 'sql';

-- Consultar aluno por matrícula
CREATE OR REPLACE FUNCTION getAlunoMatricula()
RETURNS TABLE (id int, nome varchar(20)) AS $$

	SELECT tab_matricula.id AS "Matrícula", tab_aluno.nome AS "Aluno"
	FROM tab_matricula
	LEFT JOIN tab_aluno
	ON (tab_aluno.id = tab_matricula.idaluno);

$$
LANGUAGE 'sql';

-- Consultar aluno por idade
CREATE OR REPLACE FUNCTION getAlunoIdade()
RETURNS TABLE (nome varchar, idade int) AS $$

	SELECT nome,
	((SELECT CURRENT_DATE) - nascimento)/365
	FROM tab_aluno;

$$
LANGUAGE 'sql';

-- Consultar aniversariante do mês
CREATE OR REPLACE FUNCTION getAniversarianteMes(mes integer)
RETURNS TABLE (nome varchar(20), nascimento date) AS $$

	SELECT nome, nascimento
	FROM tab_aluno WHERE Extract(Month FROM nascimento)=$1
	
$$
LANGUAGE 'sql';

-- Consultar professor por atividade
CaEATE OR REPLACE FUNCTION getProfessorAtividade(prof_atividade varchar(20))
RETURNS TABLE (id int, nome varchar(20)) AS $$
        
		SELECT tab_atividade.id AS "Atividade", tab_professor.nome AS "Professor"
		FROM tab_atividade LEFT JOIN tab_professor ON (tab_professor.id = tab_atividade.idprofessor)
		WHERE tab_atividade.nome LIKE '%' || $1 || '%'

$$
LANGUAGE 'sql';

-- Consultar turma por atividade
CREATE OR REPLACE FUNCTION getTurmaAtividade(turma_atividade varchar(20))
RETURNS TABLE (id int, nome varchar(20)) AS $$

		SELECT tab_atividade.id AS "Atividade", tab_turma.nome AS "Turma"
		FROM tab_atividade LEFT JOIN tab_turma ON (tab_turma.idatividade = tab_atividade.id)
		WHERE tab_atividade.nome LIKE '%' || $1 || '%'

$$
LANGUAGE 'sql';

-- Consultar turma por professor
CREATE OR REPLACE FUNCTION getTurmaProfessor(turma_professor varchar(20))
RETURNS TABLE (id int, nome varchar(20)) AS $$

		SELECT tab_professor.id AS "Professor", tab_turma.nome AS "Turma"
		FROM tab_professor LEFT JOIN tab_turma ON (tab_turma.idprofessor = tab_professor.id)
		WHERE tab_professor.nome LIKE '%' || $1 || '%'

$$
LANGUAGE 'sql';

-- Consultar matrícula por aluno
CREATE OR REPLACE FUNCTION getMatriculaAluno(matricula_aluno varchar(20))
RETURNS TABLE (idaluno int, id int) AS $$

		SELECT tab_aluno.id AS "Aluno", tab_matricula.id AS "Matrícula"
		FROM tab_aluno LEFT JOIN tab_matricula ON (tab_matricula.idaluno = tab_aluno.id)
		WHERE tab_aluno.nome LIKE '%' || $1 || '%'

$$
LANGUAGE 'sql';

-- Consultar matrícula por turma
CREATE OR REPLACE FUNCTION getMatriculaTurma(matricula_turma varchar(20))
RETURNS TABLE (idturma int, id int) AS $$

		SELECT tab_turma.id AS "Turma", tab_matricula.id AS "Matrícula"
		FROM tab_turma LEFT JOIN tab_matricula ON (tab_matricula.idturma = tab_turma.id)
		WHERE tab_turma.nome LIKE '%' || $1 || '%'

$$
LANGUAGE 'sql';

====================

-- Aluno: Thiago Borges