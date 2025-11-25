package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.reserva.ReservaId;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<UsuarioReservaLivro, ReservaId> {
}
