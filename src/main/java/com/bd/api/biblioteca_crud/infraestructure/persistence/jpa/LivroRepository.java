package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, String> {
}
