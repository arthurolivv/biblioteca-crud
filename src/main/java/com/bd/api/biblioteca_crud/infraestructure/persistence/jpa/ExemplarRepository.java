package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.ExemplarId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, ExemplarId> {
}
