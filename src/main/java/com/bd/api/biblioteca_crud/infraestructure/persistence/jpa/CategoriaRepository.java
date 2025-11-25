package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.livro.LivroPertenceCategoria; // Import já adicionado
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);

    @Query("""
            SELECT new com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto(
                c.id,
                c.nome,
                COUNT(lpc.livro)
            )
            FROM Categoria c
            LEFT JOIN Livro_Pertence_Categoria lpc ON lpc.categoria.id = c.id
            GROUP BY c.id, c.nome
            ORDER BY c.id ASC
            """)
    List<ListarCategoriaDto> findAllCategoriasWithLivrosCount();
}