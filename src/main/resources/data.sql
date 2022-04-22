INSERT INTO CATEGORIAS(nome_categoria)
VALUES ('informatica'), ('escritorio');

INSERT INTO SERVICOS(nome, valor)
VALUES ('Computador', 200.00), ('Mouse', 50.00), ('Impressora', 100.00);

INSERT INTO SERVICO_CATEGORIA (ID_SERVICO, ID_CATEGORIA)
VALUES(1, 1), (2, 1), (3, 2);

INSERT INTO ESTADOS(nome)
VALUES ('Paraiba'), ('São Paulo');

INSERT INTO CIDADES(nome, id_estado)
VALUES ('Cabedelo', 1), ('São Paulo', 2);

INSERT INTO CLIENTES(cpf_cnpj, email, nome, tipo_cliente)
VALUES ('22522522525', 'maria@gmail.com', 'Maria', 1),
('45689879846', 'empresa@gmail.com', 'Empresa', 2),
('45689879846', 'empresa@gmail.com', 'TATU', 2);

INSERT INTO ENDERECOS(bairro, cep, complemento, logradouro, numero, id_cidade, id_cliente)
VALUES ('pontas de mato', '2541000', 'casa', 'rua fulano', '100', 1, 1);

INSERT INTO TELEFONE(cliente_id_cliente, telefones)
VALUES (1, '82 98745 5252'), (1, '83 532 15255');

INSERT INTO ORDEMSERVICOS(data_solicitacao, id_cliente, valor_ordemservico)
values ('2022-04-02 10:32:00', 1, 308),
('2022-04-02 10:32:00', 2, 500),
('2022-04-02 10:32:00', 3, 1982);

INSERT INTO SERVICO_OS (ID_SERVICO, ID_OS)
VALUES(1, 1), (2, 2), (1, 2), (3, 3), (2, 3), (1, 3);

INSERT INTO PAGAMENTOS(id_ordemservico, status_pagamento)
values (1, 1), (2, 2), (3, 3);

INSERT INTO PAGAMENTO_BOLETO (DATA_PAGAMENTO, DATA_VENCIMENTO, ID_ORDEMSERVICO)
VALUES('2022-04-02', '2022-04-30', 1);

INSERT INTO PAGAMENTO_CARTAO  (NUMERO_PARCELAS, ID_ORDEMSERVICO)
VALUES(10, 2);

INSERT INTO PAGAMENTO_PIX (CHAVE_PIX, ID_ORDEMSERVICO)
VALUES('123456', 3);