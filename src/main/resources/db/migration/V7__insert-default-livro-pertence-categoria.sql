-- Script de inserção para livro_pertence_categoria
-- Associa cada livro às suas categorias apropriadas

-- Jane Austen
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780141439518', 3), -- Orgulho e Preconceito - Romance
                                                                          ('9780141439518', 11), -- Orgulho e Preconceito - Drama
                                                                          ('9780141439686', 3), -- Emma - Romance
                                                                          ('9780141439686', 11), -- Emma - Drama
                                                                          ('9780141439662', 3), -- Razão e Sensibilidade - Romance
                                                                          ('9780141439662', 11); -- Razão e Sensibilidade - Drama

-- George Orwell
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780451524935', 1), -- 1984 - Ficção Científica
                                                                          ('9780451524935', 11), -- 1984 - Drama
                                                                          ('9780451524935', 18), -- 1984 - Filosofia
                                                                          ('9780451526342', 1), -- A Revolução dos Bichos - Ficção Científica
                                                                          ('9780451526342', 18), -- A Revolução dos Bichos - Filosofia
                                                                          ('9780451526342', 20); -- A Revolução dos Bichos - Sociologia

-- F. Scott Fitzgerald
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780743273565', 3), -- O Grande Gatsby - Romance
                                                                          ('9780743273565', 11), -- O Grande Gatsby - Drama
                                                                          ('9780743273565', 7), -- O Grande Gatsby - História
                                                                          ('9780684801544', 3), -- Suave é a Noite - Romance
                                                                          ('9780684801544', 11); -- Suave é a Noite - Drama

-- J.D. Salinger
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780316769488', 11), -- O Apanhador no Campo de Centeio - Drama
                                                                          ('9780316769488', 19), -- O Apanhador no Campo de Centeio - Psicologia
                                                                          ('9780316769174', 11), -- Franny e Zooey - Drama
                                                                          ('9780316769174', 18); -- Franny e Zooey - Filosofia

-- Cormac McCarthy
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780307387899', 1), -- A Estrada - Ficção Científica
                                                                          ('9780307387899', 11), -- A Estrada - Drama
                                                                          ('9780307387899', 6), -- A Estrada - Aventura
                                                                          ('9780679744399', 6), -- Meridiano de Sangue - Aventura
                                                                          ('9780679744399', 7), -- Meridiano de Sangue - História
                                                                          ('9780679744399', 11); -- Meridiano de Sangue - Drama

-- Khaled Hosseini
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9781594631931', 11), -- O Caçador de Pipas - Drama
                                                                          ('9781594631931', 7), -- O Caçador de Pipas - História
                                                                          ('9781594631931', 3), -- O Caçador de Pipas - Romance
                                                                          ('9781594489501', 11), -- Mil Sóis Esplêndidos - Drama
                                                                          ('9781594489501', 7), -- Mil Sóis Esplêndidos - História
                                                                          ('9781594489501', 3); -- Mil Sóis Esplêndidos - Romance

-- Aldous Huxley
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780060850524', 1), -- Admirável Mundo Novo - Ficção Científica
                                                                          ('9780060850524', 18), -- Admirável Mundo Novo - Filosofia
                                                                          ('9780060850524', 20), -- Admirável Mundo Novo - Sociologia
                                                                          ('9780060595180', 18), -- As Portas da Percepção - Filosofia
                                                                          ('9780060595180', 19); -- As Portas da Percepção - Psicologia

-- Gabriel García Márquez
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780060883287', 2), -- Cem Anos de Solidão - Fantasia
                                                                          ('9780060883287', 11), -- Cem Anos de Solidão - Drama
                                                                          ('9780060883287', 7), -- Cem Anos de Solidão - História
                                                                          ('9780307389732', 3), -- O Amor nos Tempos do Cólera - Romance
                                                                          ('9780307389732', 11), -- O Amor nos Tempos do Cólera - Drama
                                                                          ('9780307389732', 7); -- O Amor nos Tempos do Cólera - História

-- Homer
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780140268867', 2), -- Odisseia - Fantasia
                                                                          ('9780140268867', 6), -- Odisseia - Aventura
                                                                          ('9780140268867', 7), -- Odisseia - História
                                                                          ('9780140268867', 10), -- Odisseia - Poesia
                                                                          ('9780140275360', 2), -- Ilíada - Fantasia
                                                                          ('9780140275360', 6), -- Ilíada - Aventura
                                                                          ('9780140275360', 7), -- Ilíada - História
                                                                          ('9780140275360', 10); -- Ilíada - Poesia

-- Fyodor Dostoevsky
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780143058144', 11), -- Crime e Castigo - Drama
                                                                          ('9780143058144', 18), -- Crime e Castigo - Filosofia
                                                                          ('9780143058144', 19), -- Crime e Castigo - Psicologia
                                                                          ('9780374528379', 11), -- Os Irmãos Karamazov - Drama
                                                                          ('9780374528379', 18), -- Os Irmãos Karamazov - Filosofia
                                                                          ('9780374528379', 21); -- Os Irmãos Karamazov - Religião

-- Ray Bradbury
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9781451673319', 1), -- Fahrenheit 451 - Ficção Científica
                                                                          ('9781451673319', 11), -- Fahrenheit 451 - Drama
                                                                          ('9781451673319', 18), -- Fahrenheit 451 - Filosofia
                                                                          ('9781451678192', 1), -- Crônicas Marcianas - Ficção Científica
                                                                          ('9781451678192', 2); -- Crônicas Marcianas - Fantasia

-- Harper Lee
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780061120084', 11), -- O Sol é Para Todos - Drama
                                                                          ('9780061120084', 7), -- O Sol é Para Todos - História
                                                                          ('9780061120084', 20), -- O Sol é Para Todos - Sociologia
                                                                          ('9780062409850', 11), -- Vá, Coloque um Vigia - Drama
                                                                          ('9780062409850', 20); -- Vá, Coloque um Vigia - Sociologia

-- Toni Morrison
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9781400033416', 11), -- Amada - Drama
                                                                          ('9781400033416', 7), -- Amada - História
                                                                          ('9781400033416', 5), -- Amada - Terror
                                                                          ('9781400033423', 11), -- O Olho Mais Azul - Drama
                                                                          ('9781400033423', 20); -- O Olho Mais Azul - Sociologia

-- William Golding
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780399501487', 6), -- O Senhor das Moscas - Aventura
                                                                          ('9780399501487', 11), -- O Senhor das Moscas - Drama
                                                                          ('9780399501487', 18), -- O Senhor das Moscas - Filosofia
                                                                          ('9780156027809', 1), -- Os Herdeiros - Ficção Científica
                                                                          ('9780156027809', 18); -- Os Herdeiros - Filosofia

-- William Shakespeare
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780743477123', 11), -- Hamlet - Drama
                                                                          ('9780743477123', 10), -- Hamlet - Poesia
                                                                          ('9780743477123', 18), -- Hamlet - Filosofia
                                                                          ('9780743477116', 11), -- Romeu e Julieta - Drama
                                                                          ('9780743477116', 3), -- Romeu e Julieta - Romance
                                                                          ('9780743477116', 10), -- Romeu e Julieta - Poesia
                                                                          ('9780743477109', 11), -- Macbeth - Drama
                                                                          ('9780743477109', 10), -- Macbeth - Poesia
                                                                          ('9780743477109', 5); -- Macbeth - Terror

-- J.K. Rowling
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780439708180', 2), -- Harry Potter e a Pedra Filosofal - Fantasia
                                                                          ('9780439708180', 6), -- Harry Potter e a Pedra Filosofal - Aventura
                                                                          ('9780439708180', 13), -- Harry Potter e a Pedra Filosofal - Infantil
                                                                          ('9780439064873', 2), -- Harry Potter e o Prisioneiro de Azkaban - Fantasia
                                                                          ('9780439064873', 6), -- Harry Potter e o Prisioneiro de Azkaban - Aventura
                                                                          ('9780439064873', 13); -- Harry Potter e o Prisioneiro de Azkaban - Infantil

-- Stieg Larsson
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780307454546', 4), -- Os Homens Que Não Amavam as Mulheres - Suspense
                                                                          ('9780307454546', 11), -- Os Homens Que Não Amavam as Mulheres - Drama
                                                                          ('9780307454553', 4), -- A Menina Que Brincava com Fogo - Suspense
                                                                          ('9780307454553', 11); -- A Menina Que Brincava com Fogo - Drama

-- Albert Camus
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780679720201', 11), -- O Estrangeiro - Drama
                                                                          ('9780679720201', 18), -- O Estrangeiro - Filosofia
                                                                          ('9780679720218', 11), -- A Peste - Drama
                                                                          ('9780679720218', 18), -- A Peste - Filosofia
                                                                          ('9780679720218', 20); -- A Peste - Sociologia

-- Dante Alighieri
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780142437223', 10), -- Inferno - Poesia
                                                                          ('9780142437223', 2), -- Inferno - Fantasia
                                                                          ('9780142437223', 21), -- Inferno - Religião
                                                                          ('9780142437223', 18), -- Inferno - Filosofia
                                                                          ('9780142437247', 10), -- Purgatório - Poesia
                                                                          ('9780142437247', 2), -- Purgatório - Fantasia
                                                                          ('9780142437247', 21), -- Purgatório - Religião
                                                                          ('9780142437247', 18); -- Purgatório - Filosofia

-- Herman Melville
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780143105954', 6), -- Moby Dick - Aventura
                                                                          ('9780143105954', 11), -- Moby Dick - Drama
                                                                          ('9780143105954', 18), -- Moby Dick - Filosofia
                                                                          ('9780486264738', 11), -- Bartleby, o Escrivão - Drama
                                                                          ('9780486264738', 18); -- Bartleby, o Escrivão - Filosofia

-- Paulo Coelho
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780062315007', 6), -- O Alquimista - Aventura
                                                                          ('9780062315007', 9), -- O Alquimista - Autoajuda
                                                                          ('9780062315007', 18), -- O Alquimista - Filosofia
                                                                          ('9780062315007', 21), -- O Alquimista - Religião
                                                                          ('9780061122415', 3), -- Na Margem do Rio Piedra - Romance
                                                                          ('9780061122415', 9), -- Na Margem do Rio Piedra - Autoajuda
                                                                          ('9780061122415', 21); -- Na Margem do Rio Piedra - Religião

-- Charlotte Brontë
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780141441146', 3), -- Jane Eyre - Romance
                                                                          ('9780141441146', 11), -- Jane Eyre - Drama
                                                                          ('9780199536658', 3), -- Villette - Romance
                                                                          ('9780199536658', 11); -- Villette - Drama

-- Mark Twain
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780143107323', 6), -- As Aventuras de Huckleberry Finn - Aventura
                                                                          ('9780143107323', 7), -- As Aventuras de Huckleberry Finn - História
                                                                          ('9780143107323', 12), -- As Aventuras de Huckleberry Finn - Humor
                                                                          ('9780143039563', 6), -- As Aventuras de Tom Sawyer - Aventura
                                                                          ('9780143039563', 12), -- As Aventuras de Tom Sawyer - Humor
                                                                          ('9780143039563', 13); -- As Aventuras de Tom Sawyer - Infantil

-- Johann Wolfgang von Goethe
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780143106951', 3), -- Os Sofrimentos do Jovem Werther - Romance
                                                                          ('9780143106951', 11), -- Os Sofrimentos do Jovem Werther - Drama
                                                                          ('9780143106951', 10), -- Os Sofrimentos do Jovem Werther - Poesia
                                                                          ('9780140449013', 11), -- Fausto - Drama
                                                                          ('9780140449013', 2), -- Fausto - Fantasia
                                                                          ('9780140449013', 10), -- Fausto - Poesia
                                                                          ('9780140449013', 18); -- Fausto - Filosofia

-- Leo Tolstoy
INSERT INTO livro_pertence_categoria (fk_livro_isbn, fk_categoria_id) VALUES
                                                                          ('9780143035008', 7), -- Guerra e Paz - História
                                                                          ('9780143035008', 11), -- Guerra e Paz - Drama
                                                                          ('9780143035008', 3), -- Guerra e Paz - Romance
                                                                          ('9780143035002', 3), -- Anna Karenina - Romance
                                                                          ('9780143035002', 11), -- Anna Karenina - Drama
                                                                          ('9780143035002', 18); -- Anna Karenina - Filosofia