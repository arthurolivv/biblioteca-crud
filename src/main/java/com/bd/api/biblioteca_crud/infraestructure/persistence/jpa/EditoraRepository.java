package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.editora.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, String>, JpaSpecificationExecutor<Editora> {

    // Ordenar por MAIS livros publicados
    @Query("SELECT e FROM Editora e LEFT JOIN e.livros l " +
            "GROUP BY e ORDER BY COUNT(l) DESC, e.razao_social ASC")
    List<Editora> findAllOrderByLivroCountDesc();

    // Ordenar por MENOS livros publicados
    @Query("SELECT e FROM Editora e LEFT JOIN e.livros l " +
            "GROUP BY e ORDER BY COUNT(l) ASC, e.razao_social ASC")
    List<Editora> findAllOrderByLivroCountAsc();
}