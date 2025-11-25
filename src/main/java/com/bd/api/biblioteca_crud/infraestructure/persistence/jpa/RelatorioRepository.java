package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioAutorDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioCategoriaDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioEditoraDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioLivroDto;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioStatusEmprestimoDto;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<UsuarioEmprestimoExemplar, Long> {

    // 1. Empréstimos por Categoria
    @Query("""
        SELECT new com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioCategoriaDto(c.nome, COUNT(e.id.num_emprestimo))
        FROM Usuario_Emprestimo_Exemplar e
        JOIN e.exemplar ex
        JOIN ex.livro l
        JOIN l.livroPertenceCategoria lpc
        JOIN lpc.categoria c
        GROUP BY c.nome
        ORDER BY COUNT(e.id.num_emprestimo) DESC
    """)
    List<RelatorioCategoriaDto> contarEmprestimosPorCategoria();

    // 2. Top 10 Editoras com mais livros emprestados
    @Query("""
        SELECT new com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioEditoraDto(ed.razao_social, COUNT(e.id.num_emprestimo))
        FROM Usuario_Emprestimo_Exemplar e
        JOIN e.exemplar ex
        JOIN ex.livro l
        JOIN l.editora ed
        GROUP BY ed.razao_social
        ORDER BY COUNT(e.id.num_emprestimo) DESC
        LIMIT 10
    """)
    List<RelatorioEditoraDto> encontrarTop10EditorasMaisEmprestadas();

    // 3. Top 10 Autores com mais livros emprestados
    @Query("""
        SELECT new com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioAutorDto(a.nome, COUNT(e.id.num_emprestimo))
        FROM Usuario_Emprestimo_Exemplar e
        JOIN e.exemplar ex
        JOIN ex.livro l
        JOIN l.AutorEscreveLivro ael
        JOIN ael.autor a
        GROUP BY a.nome
        ORDER BY COUNT(e.id.num_emprestimo) DESC
        LIMIT 10
    """)
    List<RelatorioAutorDto> encontrarTop10AutoresMaisEmprestados();

    // 4. Top 10 Livros Mais Emprestados
    @Query("""
        SELECT new com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioLivroDto(l.titulo, COUNT(e.id.num_emprestimo))
        FROM Usuario_Emprestimo_Exemplar e
        JOIN e.exemplar ex
        JOIN ex.livro l
        GROUP BY l.titulo
        ORDER BY COUNT(e.id.num_emprestimo) DESC
        LIMIT 10
    """)
    List<RelatorioLivroDto> encontrarTop10LivrosMaisEmprestados();

    // 5. Empréstimos por Status (Aberto, Atraso, Devolvido) - CORRIGIDO
    @Query("""
        SELECT new com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioStatusEmprestimoDto(
            CASE
                WHEN e.data_devolucao IS NOT NULL THEN 'DEVOLVIDO'
                WHEN CURRENT_DATE > e.data_devolucao_prevista THEN 'EM_ATRASO'
                ELSE 'EM_ABERTO'
            END,
            COUNT(e.id.num_emprestimo)
        )
        FROM Usuario_Emprestimo_Exemplar e
        GROUP BY
            CASE
                WHEN e.data_devolucao IS NOT NULL THEN 'DEVOLVIDO'
                WHEN CURRENT_DATE > e.data_devolucao_prevista THEN 'EM_ATRASO'
                ELSE 'EM_ABERTO'
            END
    """)
    List<RelatorioStatusEmprestimoDto> contarEmprestimosPorStatus();
}