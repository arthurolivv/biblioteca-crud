package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.shared.enums.Nacionalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String>, JpaSpecificationExecutor<Autor> {

    @Query("SELECT a FROM Autor a LEFT JOIN a.escreve e " +
            "WHERE (:nacionalidade IS NULL OR a.nacionalidade = :nacionalidade) " +
            "GROUP BY a ORDER BY COUNT(e) DESC, a.nome ASC")
    List<Autor> findAllOrderByLivroCountDescFiltered(@Param("nacionalidade") Nacionalidade nacionalidade);

    // Ordenar por MENOS livros, filtrando por Nacionalidade
    @Query("SELECT a FROM Autor a LEFT JOIN a.escreve e " +
            "WHERE (:nacionalidade IS NULL OR a.nacionalidade = :nacionalidade) " +
            "GROUP BY a ORDER BY COUNT(e) ASC, a.nome ASC")
    List<Autor> findAllOrderByLivroCountAscFiltered(@Param("nacionalidade") Nacionalidade nacionalidade);
}

