package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<UsuarioEmprestimoExemplar, EmprestimoId> {
    @Query("SELECT COUNT(e) FROM Usuario_Emprestimo_Exemplar e WHERE e.usuario.cpf = :cpf")
    Long contarEmprestimosPorUsuario(String cpf);
}
