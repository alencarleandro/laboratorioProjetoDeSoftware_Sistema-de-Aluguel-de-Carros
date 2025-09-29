-- ================================
-- INSERIR USUÁRIOS (Clientes/Admins)
-- ================================
INSERT INTO usuarios (nome, email, senha, cpf, rg, cep, numero, complemento, score_credito)
VALUES ('Administrador', 'admin@email.com', '$2b$12$w6rblQYLEx8oqcs.O20L5.kuCIFhcCeGYtnPTh8jB4a4La0jcdHWa', '000.000.000-00', '00.000.000-0', '00000-000', '0', 'N/A', 750);

INSERT INTO usuarios (nome, email, senha, cpf, rg, cep, numero, complemento, score_credito)
VALUES ('Cliente Fulano', 'cliente@email.com', '$2b$12$MI2Ll.I8mTK7xiz.2Uk3YOO4irY50TG1HgmTUKj4jSykuAfDn5Goi', '111.222.333-44', '11.222.333-4', '12345-678', '123', 'Apto 101', 800);

-- ================================
-- INSERIR AUTOMÓVEIS
-- ================================
INSERT INTO automovel (matricula, ano, marca, modelo, placa) VALUES ('MOBI2023', 2023, 'Fiat', 'Mobi', 'ABC-1A23');
INSERT INTO automovel (matricula, ano, marca, modelo, placa) VALUES ('HB2024', 2024, 'Hyundai', 'HB20', 'DEF-4B56');
INSERT INTO automovel (matricula, ano, marca, modelo, placa) VALUES ('REN2023', 2023, 'Jeep', 'Renegade', 'GHI-7C89');

-- ================================
-- INSERIR AGENTES
-- ================================
INSERT INTO agente (nome) VALUES ('Banco Aluga+');
INSERT INTO agente (nome) VALUES ('Empresa Carros S/A');

-- ================================
-- INSERIR PEDIDOS DE ALUGUEL
-- ================================
INSERT INTO pedidos_de_aluguel (data_emissao, data_inicio, data_fim, status, usuario_id, agente_id, automovel_id, preco)
VALUES (CURRENT_TIMESTAMP, '2025-10-20', '2025-10-25', 'PENDENTE', 2, 1, 1, 150.00);
