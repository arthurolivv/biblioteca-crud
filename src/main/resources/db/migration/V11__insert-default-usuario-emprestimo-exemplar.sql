

-- Ana Silva (123.456.789-01) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '123.456.789-01', '2024-10-01', '2024-10-07', '2024-10-08', '97807739518', '97807739518', 'EX-1'),
                                                                                                                                                                                               (2, '123.456.789-01', '2024-10-20', '2024-10-26', '2024-10-27', '9780451524935', '9780451524935', 'EX-2'),
                                                                                                                                                                                               (3, '123.456.789-01', '2025-11-18', NULL, '2025-11-25', '9780743273565', '9780743273565', 'EX-1');

-- Bruno Santos (234.567.890-12) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '234.567.890-12', '2024-09-15', '2024-09-21', '2024-09-22', '9780316769488', '9780316769488', 'EX-3'),
                                                                                                                                                                                               (2, '234.567.890-12', '2024-10-25', '2024-11-01', '2024-11-01', '9780307387899', '9780307387899', 'EX-4'),
                                                                                                                                                                                               (3, '234.567.890-12', '2025-11-20', NULL, '2025-11-27', '97807739662', '97807739662', 'EX-1');

-- Carla Oliveira (345.678.901-23) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '345.678.901-23', '2024-10-05', '2024-10-11', '2024-10-12', '9781594631931', '9781594631931', 'EX-2'),
                                                                                                                                                                                               (2, '345.678.901-23', '2024-10-25', NULL, '2024-11-01', '9780060850524', '9780060850524', 'EX-1'),
                                                                                                                                                                                               (3, '345.678.901-23', '2025-11-19', NULL, '2025-11-26', '97807739686', '97807739686', 'EX-3');

-- Diego Costa (456.789.012-34) - 2 empréstimos (1 em atraso de 2024)
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '456.789.012-34', '2024-10-15', NULL, '2024-10-22', '9780060883287', '9780060883287', 'EX-2'),
                                                                                                                                                                                               (2, '456.789.012-34', '2025-11-17', NULL, '2025-11-24', '9780451526342', '9780451526342', 'EX-1');

-- Eduarda Lima (567.890.123-45) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '567.890.123-45', '2024-09-20', '2024-09-26', '2024-09-27', '978070268867', '978070268867', 'EX-1'),
                                                                                                                                                                                               (2, '567.890.123-45', '2024-10-10', '2024-10-16', '2024-10-17', '97807305874', '97807305874', 'EX-2'),
                                                                                                                                                                                               (3, '567.890.123-45', '2025-11-21', NULL, '2025-11-28', '978751673319', '978751673319', 'EX-4');

-- Felipe Pereira (678.901.234-56) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '678.901.234-56', '2024-09-10', '2024-09-16', '2024-09-17', '97807739662', '97807739662', 'EX-2'),
                                                                                                                                                                                               (2, '678.901.234-56', '2024-10-05', '2024-10-11', '2024-10-12', '9780679720218', '9780679720218', 'EX-1'),
                                                                                                                                                                                               (3, '678.901.234-56', '2025-11-19', NULL, '2025-11-26', '978751678192', '978751678192', 'EX-1');

-- Gabriela Almeida (789.012.345-67) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '789.012.345-67', '2024-10-18', '2024-10-24', '2024-10-25', '9780061120084', '9780061120084', 'EX-2'),
                                                                                                                                                                                               (2, '789.012.345-67', '2024-11-12', '2024-11-18', '2024-11-19', '978700033416', '978700033416', 'EX-5'),
                                                                                                                                                                                               (3, '789.012.345-67', '2025-11-22', NULL, '2025-11-29', '9780062409850', '9780062409850', 'EX-1');

-- Henrique Rocha (890.123.456-78) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '890.123.456-78', '2024-09-25', '2024-10-01', '2024-10-02', '978039950787', '978039950787', 'EX-3'),
                                                                                                                                                                                               (2, '890.123.456-78', '2024-10-28', NULL, '2024-11-04', '9780743477123', '9780743477123', 'EX-1'),
                                                                                                                                                                                               (3, '890.123.456-78', '2025-11-20', NULL, '2025-11-27', '9780316769174', '9780316769174', 'EX-3');

-- Isabela Martins (901.234.567-89) - 3 empréstimos (todos devolvidos)
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '901.234.567-89', '2024-08-15', '2024-08-21', '2024-08-22', '978073039563', '978073039563', 'EX-1'),
                                                                                                                                                                                               (2, '901.234.567-89', '2024-09-05', '2024-09-11', '2024-09-12', '9780199536658', '9780199536658', 'EX-2'),
                                                                                                                                                                                               (3, '901.234.567-89', '2024-10-01', '2024-10-07', '2024-10-08', '9780486264738', '9780486264738', 'EX-3');

-- João Fernandes (012.345.678-90) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '012.345.678-90', '2024-10-15', '2024-10-21', '2024-10-22', '9780439708180', '9780439708180', 'EX-3'),
                                                                                                                                                                                               (2, '012.345.678-90', '2024-11-08', NULL, '2024-11-15', '9780439064873', '9780439064873', 'EX-2'),
                                                                                                                                                                                               (3, '012.345.678-90', '2025-11-20', NULL, '2025-11-27', '9780684801544', '9780684801544', 'EX-2');

-- Julia Cardoso (123.456.780-91) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '123.456.780-91', '2024-10-02', '2024-10-08', '2024-10-09', '9780307454546', '9780307454546', 'EX-4'),
                                                                                                                                                                                               (2, '123.456.780-91', '2024-10-22', '2024-10-28', '2024-10-29', '9780679720201', '9780679720201', 'EX-3'),
                                                                                                                                                                                               (3, '123.456.780-91', '2025-11-19', NULL, '2025-11-26', '978072437223', '978072437223', 'EX-6');

-- Kevin Sousa (234.567.891-02) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '234.567.891-02', '2024-10-20', '2024-10-26', '2024-10-27', '978073105954', '978073105954', 'EX-2'),
                                                                                                                                                                                               (2, '234.567.891-02', '2025-11-21', NULL, '2025-11-28', '9780307454553', '9780307454553', 'EX-1');

-- Larissa Ribeiro (345.678.902-13) - 2 empréstimos em atraso
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '345.678.902-13', '2024-10-10', NULL, '2024-10-17', '9780062315007', '9780062315007', 'EX-3'),
                                                                                                                                                                                               (2, '345.678.902-13', '2024-10-20', NULL, '2024-10-27', '9780774176', '9780774176', 'EX-4');

-- Marcos Araujo (456.789.013-24) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '456.789.013-24', '2024-09-18', '2024-09-24', '2024-09-25', '978072437247', '978072437247', 'EX-5'),
                                                                                                                                                                                               (2, '456.789.013-24', '2025-11-22', NULL, '2025-11-29', '978070275360', '978070275360', 'EX-2');

-- Natália Gomes (567.890.124-35) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '567.890.124-35', '2024-09-28', '2024-10-04', '2024-10-05', '978073107323', '978073107323', 'EX-2'),
                                                                                                                                                                                               (2, '567.890.124-35', '2024-10-30', '2024-11-05', '2024-11-06', '978073106951', '978073106951', 'EX-3'),
                                                                                                                                                                                               (3, '567.890.124-35', '2025-11-20', NULL, '2025-11-27', '978073035008', '978073035008', 'EX-1');

-- Otávio Castro (678.901.235-46) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '678.901.235-46', '2024-10-25', '2024-10-31', '2024-11-01', '978070449013', '978070449013', 'EX-7'),
                                                                                                                                                                                               (2, '678.901.235-46', '2025-11-22', NULL, '2025-11-29', '9780374528379', '9780374528379', 'EX-2');

-- Patricia Dias (789.012.346-57) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '789.012.346-57', '2024-10-08', '2024-10-14', '2024-10-15', '9780684801544', '9780684801544', 'EX-1'),
                                                                                                                                                                                               (2, '789.012.346-57', '2024-11-05', '2024-11-11', '2024-11-12', '9780316769174', '9780316769174', 'EX-5'),
                                                                                                                                                                                               (3, '789.012.346-57', '2025-11-23', NULL, '2025-11-30', '9780307389732', '9780307389732', 'EX-1');

-- Rafael Moreira (890.123.457-68) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '890.123.457-68', '2024-10-10', '2024-10-16', '2024-10-17', '9780061122415', '9780061122415', 'EX-2'),
                                                                                                                                                                                               (2, '890.123.457-68', '2025-11-18', NULL, '2025-11-25', '978073035002', '978073035002', 'EX-3');

-- Sabrina Nunes (901.234.568-79) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '901.234.568-79', '2024-10-12', '2024-10-18', '2024-10-19', '9780679744399', '9780679744399', 'EX-3'),
                                                                                                                                                                                               (2, '901.234.568-79', '2024-11-02', '2024-11-08', '2024-11-09', '9781594489501', '9781594489501', 'EX-2'),
                                                                                                                                                                                               (3, '901.234.568-79', '2025-11-22', NULL, '2025-11-29', '9780060595180', '9780060595180', 'EX-1');

-- Thiago Barbosa (012.345.679-80) - 2 empréstimos em atraso de 2024
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '012.345.679-80', '2024-10-25', NULL, '2024-11-01', '9780307389732', '9780307389732', 'EX-2'),
                                                                                                                                                                                               (2, '012.345.679-80', '2024-11-15', NULL, '2024-11-22', '978070275360', '978070275360', 'EX-3');

-- Úrsula Mendes (123.456.781-01) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '123.456.781-01', '2024-10-18', '2024-10-24', '2024-10-25', '9780374528379', '9780374528379', 'EX-4'),
                                                                                                                                                                                               (2, '123.456.781-01', '2025-11-21', NULL, '2025-11-28', '97807305874', '97807305874', 'EX-4');

-- Vitor Teixeira (234.567.892-12) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '234.567.892-12', '2024-08-20', '2024-08-26', '2024-08-27', '9780307454553', '9780307454553', 'EX-4'),
                                                                                                                                                                                               (2, '234.567.892-12', '2024-09-25', '2024-10-01', '2024-10-02', '9780743477109', '9780743477109', 'EX-2'),
                                                                                                                                                                                               (3, '234.567.892-12', '2025-11-23', NULL, '2025-11-30', '978700033423', '978700033423', 'EX-7');

-- Wanda Cavalcanti (345.678.903-23) - 3 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '345.678.903-23', '2024-09-30', '2024-10-06', '2024-10-07', '978751678192', '978751678192', 'EX-3'),
                                                                                                                                                                                               (2, '345.678.903-23', '2024-10-26', '2024-11-01', '2024-11-02', '9780062409850', '9780062409850', 'EX-2'),
                                                                                                                                                                                               (3, '345.678.903-23', '2025-11-17', NULL, '2025-11-24', '978700033423', '978700033423', 'EX-6');

-- Xavier Azevedo (456.789.014-34) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '456.789.014-34', '2024-10-15', '2024-10-21', '2024-10-22', '9780156027809', '9780156027809', 'EX-1'),
                                                                                                                                                                                               (2, '456.789.014-34', '2025-11-19', NULL, '2025-11-26', '9780743477116', '9780743477116', 'EX-4');

-- Yasmin Freitas (567.890.125-45) - 2 empréstimos
INSERT INTO Usuario_Emprestimo_Exemplar (num_emprestimo, fk_usuario_cpf, data_emprestimo, data_devolucao, data_devolucao_prevista, livro_isbn, fk_livro_isbn, fk_exemplar_codigo_exemplar) VALUES
                                                                                                                                                                                               (1, '567.890.125-45', '2024-11-10', '2024-11-16', '2024-11-17', '9780743477109', '9780743477109', 'EX-3'),
                                                                                                                                                                                               (2, '567.890.125-45', '2025-11-21', NULL, '2025-11-28', '9780156027809', '9780156027809', 'EX-2');

