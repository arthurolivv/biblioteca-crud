package com.bd.api.biblioteca_crud.application.emprestimo.service;

import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.ExemplarId;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EmprestimoRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Service
public class NovoEmprestimoUsecase {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;


    public void execute(String cpf,
                        String isbn_exemplar,
                        String codigo_exemplar,
                        LocalDate data_emprestimo,
                        LocalDate data_devolucao_prevista){

        Usuario usuario = usuarioRepository.getReferenceById(cpf);
        ExemplarId exemplarId = new ExemplarId(isbn_exemplar, codigo_exemplar);
        Exemplar exemplar = exemplarRepository.getReferenceById(exemplarId);

        Long totalEmprestimos = emprestimoRepository.contarEmprestimosPorUsuario(cpf);
        Long proximoNumero = totalEmprestimos + 1;

        EmprestimoId emprestimoId = new EmprestimoId(proximoNumero, cpf);

        UsuarioEmprestimoExemplar emprestimo = new UsuarioEmprestimoExemplar();
        emprestimo.setId(emprestimoId);
        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplar);
        emprestimo.setLivro_isbn(isbn_exemplar);
        emprestimo.setData_emprestimo(data_emprestimo);
        emprestimo.setData_devolucao_prevista(data_devolucao_prevista);

        exemplar.setStatus(StatusExemplar.EMPRESTADO);

        emprestimoRepository.save(emprestimo);

    }


}
