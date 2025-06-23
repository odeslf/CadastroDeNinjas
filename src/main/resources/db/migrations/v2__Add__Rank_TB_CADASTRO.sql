--Migration para adicionar a coluna de RANK ma tabela de cadastros

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);