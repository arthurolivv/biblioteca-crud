package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, String> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    @Query("""
            SELECT DISTINCT l 
            FROM Livro l
            LEFT JOIN l.livroPertenceCategoria lpc
            WHERE lpc.categoria.id = :categoriaId""")
    List<Livro> findByCategoria(@Param("categoriaId") Long categoriaId);


    @Query("""
            SELECT DISTINCT l
            FROM Livro l
            WHERE l.deleted = :status""")
    List<Livro> findByStatus(@Param("status") Boolean status);

    List<Livro> findAllByOrderByTituloAsc();

    List<Livro> findAllByOrderByTituloDesc();
}
