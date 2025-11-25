package com.bd.api.biblioteca_crud.application.emprestimo.service;

import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.ExemplarId;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EmprestimoRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConcluirEmprestimoUsecase {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    public void execute(Long num_emprestimo, String cpf, String isbn_exemplar, String codigo_exemplar){

        EmprestimoId emprestimoId = new EmprestimoId(num_emprestimo, cpf);
        UsuarioEmprestimoExemplar concluirEmprestimo = emprestimoRepository.getReferenceById(emprestimoId);
        LocalDate data_devolucao = LocalDate.now();
        concluirEmprestimo.setData_devolucao(data_devolucao);

        emprestimoRepository.save(concluirEmprestimo);

        ExemplarId exemplarId = new ExemplarId(isbn_exemplar, codigo_exemplar);
        Exemplar exemplar = exemplarRepository.getReferenceById(exemplarId);
        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        exemplarRepository.save(exemplar);
    }
}
