INSERT INTO CATEGORIAS(nome_categoria)
VALUES ('Marketing Digital'), ('Materiais Gráficos'), ('Desenvolvimento');

INSERT INTO SERVICOS(nome, valor) VALUES ('Rede Social', 1000.00), ('Inbound', 2000.00),
('Trafego Pago', 500.00), ('SEO', 500.00), ('Cartão de Vista', 100.00), ('Logotipo', 500.00),
('Folder', 100.00), ('Sites', 1500.00), ('UX/UI', 1000.00);

INSERT INTO SERVICO_CATEGORIA (ID_SERVICO, ID_CATEGORIA)
VALUES(1, 1), (2, 1), (3, 1), (4, 1),
(5, 2), (6, 2), (7, 2), (8, 3), (9, 3);

INSERT INTO ESTADOS(nome)
VALUES ('Paraiba'), ('São Paulo');

INSERT INTO CIDADES(nome, id_estado)
VALUES ('Cabedelo', 1), ('São Paulo', 2);

INSERT INTO CLIENTES(cpf_cnpj, email, nome, tipo_cliente)
VALUES ('30853569916', 'maria@gmail.com', 'Maria Lins', 1),
('06394931306', 'joao@gmail.com', 'João Pedro', 1),
('46572760260', 'Otavio@gmail.com', 'Otavio Luis', 1),
('93.890.449/0001-53', 'empresaONE@gmail.com', 'ONE', 2),
('12.485.386/0001-58', 'empresaBOSS@gmail.com', 'BOSS', 2);

INSERT INTO ENDERECOS(bairro, cep, complemento, logradouro, numero, id_cidade, id_cliente)
VALUES ('pontas de mato', '2541000', 'casa', 'rua Agemiro Figuiredo', '100', 1, 1),
('centro', '58231422', 'casa', 'rua Pedro Cardoso', '56', 1, 2),
('centro', '6523123', 'Apto. 101', 'rua Honório Silva', '98', 2, 3),
('Camalau', '536252', 'Empresarial Dimas', 'rua Tangredo Neves', '74', 1, 4),
('Alto Monte', '956523', 'Empresarial Cabloco', 'Av. Paulista', '23', 2, 5);

INSERT INTO TELEFONE(cliente_id_cliente, telefones)
VALUES (1, '83 98745 5252'),
(2, '83 98712 4546'),
(3, '11 98723 4151'),
(4, '83 98632 5656'),
(5, '81 98632 5555');

INSERT INTO ORDEMSERVICOS(data_solicitacao, id_cliente)
values ('2022-04-02 10:32:00', 1),
('2022-04-02 10:32:00', 2),
('2022-04-02 10:32:00', 3);


INSERT INTO PAGAMENTOS(id_ordemservico, status_pagamento)
values (1, 1), (2, 2), (3, 3);

INSERT INTO PAGAMENTO_BOLETO (DATA_PAGAMENTO, DATA_VENCIMENTO, ID_ORDEMSERVICO)
VALUES('2022-04-02', '2022-04-30', 1);

INSERT INTO PAGAMENTO_CARTAO  (NUMERO_PARCELAS, ID_ORDEMSERVICO)
VALUES(10, 2);

INSERT INTO PAGAMENTO_PIX (CHAVE_PIX, ID_ORDEMSERVICO)
VALUES('123456', 3);