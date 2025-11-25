package com.bd.api.biblioteca_crud.application.emprestimo.service;

import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RenovarEmprestimoUsecase {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public void execute(Long num_emprestimo, String cpf){

        EmprestimoId emprestimoId = new EmprestimoId(num_emprestimo, cpf);
        UsuarioEmprestimoExemplar renovarEmprestimo = emprestimoRepository.getReferenceById(emprestimoId);
        LocalDate novaDataPrevista = renovarEmprestimo.getData_devolucao_prevista().plusDays(7);
        renovarEmprestimo.setData_devolucao_prevista(novaDataPrevista);
        emprestimoRepository.save(renovarEmprestimo);

    }
}
