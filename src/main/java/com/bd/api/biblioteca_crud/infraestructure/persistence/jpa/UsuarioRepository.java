package com.bd.api.biblioteca_crud.infraestructure.persistence.jpa;

import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario> {


    // Ordenar por quantidade de empréstimos ativos (DESC)
    @Query("SELECT u FROM Usuario u LEFT JOIN u.emprestimos e " +
            "WHERE e.data_devolucao IS NULL OR e.data_devolucao > CURRENT_TIMESTAMP " +
            "GROUP BY u ORDER BY COUNT(e) DESC, u.nome.priNome ASC")
    List<Usuario> findAllOrderByEmprestimosDesc();

    @Query("SELECT u FROM Usuario u LEFT JOIN u.emprestimos e " +
            "WHERE e.data_devolucao IS NULL OR e.data_devolucao > CURRENT_TIMESTAMP " +
            "GROUP BY u ORDER BY COUNT(e) ASC, u.nome.priNome ASC")
    List<Usuario> findAllOrderByEmprestimosAsc();

    @Query("SELECT u FROM Usuario u LEFT JOIN u.reservas r " +
            "WHERE r.status = 'PENDENTE' " +
            "GROUP BY u ORDER BY COUNT(r) DESC, u.nome.priNome ASC")
    List<Usuario> findAllOrderByReservasDesc();

    @Query("SELECT u FROM Usuario u LEFT JOIN u.reservas r " +
            "WHERE r.status = 'PENDENTE' " +
            "GROUP BY u ORDER BY COUNT(r) ASC, u.nome.priNome ASC")
    List<Usuario> findAllOrderByReservasAsc();
}