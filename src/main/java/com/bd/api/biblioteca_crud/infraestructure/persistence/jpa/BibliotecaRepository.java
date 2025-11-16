package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.biblioteca.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, String> {
}
