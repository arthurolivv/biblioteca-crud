package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}
