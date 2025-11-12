-- Script de inserção de exemplares
-- Cada livro terá exemplares numerados de 'EX-1' até a quantidade disponível
-- Todos os exemplares são próprios da biblioteca e estão disponíveis

-- Jane Austen - Orgulho e Preconceito ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '97807739518', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '97807739518', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '97807739518', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '97807739518', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '97807739518', 'DISPONIVEL');

-- Jane Austen - Emma ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '97807739686', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '97807739686', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '97807739686', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '97807739686', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '97807739686', 'DISPONIVEL');

-- Jane Austen - Razão e Sensibilidade ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '97807739662', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '97807739662', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '97807739662', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '97807739662', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '97807739662', 'DISPONIVEL');

-- George Orwell - 1984 ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780451524935', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780451524935', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780451524935', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780451524935', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780451524935', 'DISPONIVEL');

-- George Orwell - A Revolução dos Bichos ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780451526342', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780451526342', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780451526342', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780451526342', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780451526342', 'DISPONIVEL');

-- F. Scott Fitzgerald - O Grande Gatsby ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780743273565', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780743273565', 'DISPONIVEL');

-- F. Scott Fitzgerald - Suave é a Noite ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780684801544', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780684801544', 'DISPONIVEL');

-- J.D. Salinger - O Apanhador no Campo de Centeio ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780316769488', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780316769488', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780316769488', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780316769488', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780316769488', 'DISPONIVEL');

-- J.D. Salinger - Franny e Zooey ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780316769174', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780316769174', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780316769174', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780316769174', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780316769174', 'DISPONIVEL');

-- Cormac McCarthy - A Estrada ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780307387899', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780307387899', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780307387899', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780307387899', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780307387899', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780307387899', 'DISPONIVEL');

-- Cormac McCarthy - Meridiano de Sangue ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780679744399', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780679744399', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780679744399', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780679744399', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780679744399', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780679744399', 'DISPONIVEL');

-- Khaled Hosseini - O Caçador de Pipas ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9781594631931', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9781594631931', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9781594631931', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9781594631931', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9781594631931', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9781594631931', 'DISPONIVEL');

-- Khaled Hosseini - Mil Sóis Esplêndidos ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9781594489501', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9781594489501', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9781594489501', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9781594489501', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9781594489501', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9781594489501', 'DISPONIVEL');

-- Aldous Huxley - Admirável Mundo Novo ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780060850524', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780060850524', 'DISPONIVEL');

-- Aldous Huxley - As Portas da Percepção ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780060595180', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780060595180', 'DISPONIVEL');

-- Gabriel García Márquez - Cem Anos de Solidão ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780060883287', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780060883287', 'DISPONIVEL');

-- Gabriel García Márquez - O Amor nos Tempos do Cólera ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780307389732', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780307389732', 'DISPONIVEL');

-- Homer - Odisseia ('EX-3' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978070268867', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978070268867', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978070268867', 'DISPONIVEL');

-- Homer - Ilíada ('EX-3' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978070275360', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978070275360', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978070275360', 'DISPONIVEL');

-- Fyodor Dostoevsky - Crime e Castigo ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '97807305874', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '97807305874', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '97807305874', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '97807305874', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '97807305874', 'DISPONIVEL');

-- Fyodor Dostoevsky - Os Irmãos Karamazov ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780374528379', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780374528379', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780374528379', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780374528379', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780374528379', 'DISPONIVEL');

-- Ray Bradbury - Fahrenheit 451 ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978751673319', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978751673319', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978751673319', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978751673319', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978751673319', 'DISPONIVEL');

-- Ray Bradbury - Crônicas Marcianas ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978751678192', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978751678192', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978751678192', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978751678192', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978751678192', 'DISPONIVEL');

-- Harper Lee - O Sol é Para Todos ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780061120084', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780061120084', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780061120084', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780061120084', 'DISPONIVEL');

-- Harper Lee - Vá, Coloque um Vigia ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780062409850', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780062409850', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780062409850', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780062409850', 'DISPONIVEL');

-- Toni Morrison - Amada ('EX-8' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '978700033416', 'DISPONIVEL'),
                                                                           (false, 'EX-8', '978700033416', 'DISPONIVEL');

-- Toni Morrison - O Olho Mais Azul ('EX-8' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '978700033423', 'DISPONIVEL'),
                                                                           (false, 'EX-8', '978700033423', 'DISPONIVEL');

-- William Golding - O Senhor das Moscas ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978039950787', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978039950787', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978039950787', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978039950787', 'DISPONIVEL');

-- William Golding - Os Herdeiros ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780156027809', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780156027809', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780156027809', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780156027809', 'DISPONIVEL');

-- William Shakespeare - Hamlet ('EX-1' exemplar)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
    (false, 'EX-1', '9780743477123', 'DISPONIVEL');

-- William Shakespeare - Romeu e Julieta ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780743477116', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780743477116', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780743477116', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780743477116', 'DISPONIVEL');

-- William Shakespeare - Macbeth ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780743477109', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780743477109', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780743477109', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780743477109', 'DISPONIVEL');

-- J.K. Rowling - Harry Potter e a Pedra Filosofal ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780439708180', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780439708180', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780439708180', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780439708180', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780439708180', 'DISPONIVEL');

-- J.K. Rowling - Harry Potter e o Prisioneiro de Azkaban ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780439064873', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780439064873', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780439064873', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780439064873', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780439064873', 'DISPONIVEL');

-- Stieg Larsson - Os Homens Que Não Amavam as Mulheres ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780307454546', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780307454546', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780307454546', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780307454546', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780307454546', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780307454546', 'DISPONIVEL');

-- Stieg Larsson - A Menina Que Brincava com Fogo ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780307454553', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780307454553', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780307454553', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780307454553', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780307454553', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780307454553', 'DISPONIVEL');

-- Albert Camus - O Estrangeiro ('EX-7' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780679720201', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '9780679720201', 'DISPONIVEL');

-- Albert Camus - A Peste ('EX-3' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780679720218', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780679720218', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780679720218', 'DISPONIVEL');

-- Dante Alighieri - Inferno ('EX-8' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '978072437223', 'DISPONIVEL'),
                                                                           (false, 'EX-8', '978072437223', 'DISPONIVEL');

-- Dante Alighieri - Purgatório ('EX-8' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '978072437247', 'DISPONIVEL'),
                                                                           (false, 'EX-8', '978072437247', 'DISPONIVEL');

-- Herman Melville - Moby Dick ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073105954', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073105954', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978073105954', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978073105954', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978073105954', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978073105954', 'DISPONIVEL');

-- Herman Melville - Bartleby, o Escrivão ('EX-6' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780486264738', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780486264738', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780486264738', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780486264738', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780486264738', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '9780486264738', 'DISPONIVEL');

-- Paulo Coelho - O Alquimista ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780062315007', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780062315007', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780062315007', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780062315007', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780062315007', 'DISPONIVEL');

-- Paulo Coelho - Na Margem do Rio Piedra Eu Sentei e Chorei ('EX-4' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780061122415', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780061122415', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780061122415', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780061122415', 'DISPONIVEL');

-- Charlotte Brontë - Jane Eyre ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780774176', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780774176', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780774176', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '9780774176', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '9780774176', 'DISPONIVEL');

-- Charlotte Brontë - Villette ('EX-3' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '9780199536658', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '9780199536658', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '9780199536658', 'DISPONIVEL');

-- Mark Twain - As Aventuras de Huckleberry Finn ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073107323', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073107323', 'DISPONIVEL');

-- Mark Twain - As Aventuras de Tom Sawyer ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073039563', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073039563', 'DISPONIVEL');

-- Johann Wolfgang von Goethe - Os Sofrimentos do Jovem Werther ('EX-3' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073106951', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073106951', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978073106951', 'DISPONIVEL');

-- Johann Wolfgang von Goethe - Fausto ('EX-9' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-6', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-7', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-8', '978070449013', 'DISPONIVEL'),
                                                                           (false, 'EX-9', '978070449013', 'DISPONIVEL');

-- Leo Tolstoy - Guerra e Paz ('EX-2' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073035008', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073035008', 'DISPONIVEL');

-- Leo Tolstoy - Anna Karenina ('EX-5' exemplares)
INSERT INTO exemplar (proprio, codigo_exemplar, fk_livro_isbn, status) VALUES
                                                                           (false, 'EX-1', '978073035002', 'DISPONIVEL'),
                                                                           (false, 'EX-2', '978073035002', 'DISPONIVEL'),
                                                                           (false, 'EX-3', '978073035002', 'DISPONIVEL'),
                                                                           (false, 'EX-4', '978073035002', 'DISPONIVEL'),
                                                                           (false, 'EX-5', '978073035002', 'DISPONIVEL');