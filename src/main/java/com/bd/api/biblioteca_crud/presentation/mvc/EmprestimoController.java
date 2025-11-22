package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.emprestimo.service.NovoEmprestimoUsecase;
import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.exemplar.ExemplarId;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EmprestimoRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ExemplarRepository exemplarRepository;

    @PostMapping("/salvar")
    public String salvarEmprestimo(
            @RequestParam String cpf,
            @RequestParam String isbn_exemplar,
            @RequestParam String codigo_exemplar,
            @RequestParam String data_emprestimo,
            @RequestParam String data_devolucao_prevista
    ) {

        Usuario usuario = usuarioRepository.getReferenceById(cpf);

        ExemplarId exemplarId = new ExemplarId(isbn_exemplar, codigo_exemplar);
        Exemplar exemplar = exemplarRepository.getReferenceById(exemplarId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEmp = LocalDate.parse(data_emprestimo, formatter);
        LocalDate dataPrev = LocalDate.parse(data_devolucao_prevista, formatter);

        Long totalEmprestimos = emprestimoRepository.contarEmprestimosPorUsuario(cpf);
        Long proximoNumero = totalEmprestimos + 1;

        EmprestimoId emprestimoId = new EmprestimoId(proximoNumero, cpf);

        UsuarioEmprestimoExemplar emprestimo = new UsuarioEmprestimoExemplar();
        emprestimo.setId(emprestimoId);
        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplar);
        emprestimo.setLivro_isbn(isbn_exemplar);
        emprestimo.setData_emprestimo(dataEmp);
        emprestimo.setData_devolucao_prevista(dataPrev);

        exemplar.setStatus(StatusExemplar.EMPRESTADO);

        emprestimoRepository.save(emprestimo);

        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }
}
