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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ExemplarRepository exemplarRepository;

    private final NovoEmprestimoUsecase emprestimo;

    @PostMapping("/salvar")
    public String salvarEmprestimo(
            @RequestParam String cpf,
            @RequestParam String isbn_exemplar,
            @RequestParam String codigo_exemplar,
            @RequestParam String data_emprestimo,
            @RequestParam String data_devolucao_prevista
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEmp = LocalDate.parse(data_emprestimo, formatter);
        LocalDate dataPrev = LocalDate.parse(data_devolucao_prevista, formatter);

        emprestimo.execute(cpf, isbn_exemplar, codigo_exemplar, dataEmp, dataPrev);

        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }

    @PostMapping("/renovar")
    public String renovarEmprestimo(
            @RequestParam String cpf,
            @RequestParam Long num_emprestimo
    ) {

        EmprestimoId emprestimoId = new EmprestimoId(num_emprestimo, cpf);
        UsuarioEmprestimoExemplar renovarEmprestimo = emprestimoRepository.getReferenceById(emprestimoId);
        LocalDate novaDataPrevista = renovarEmprestimo.getData_devolucao_prevista().plusDays(7);
        renovarEmprestimo.setData_devolucao_prevista(novaDataPrevista);
        emprestimoRepository.save(renovarEmprestimo);

        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }

    @PostMapping("/concluir")
    public String concluirEmprestimo(
            @RequestParam String cpf,
            @RequestParam Long num_emprestimo,
            @RequestParam String isbn_exemplar,
            @RequestParam String codigo_exemplar
    ) {

        EmprestimoId emprestimoId = new EmprestimoId(num_emprestimo, cpf);
        UsuarioEmprestimoExemplar concluirEmprestimo = emprestimoRepository.getReferenceById(emprestimoId);
        LocalDate data_devolucao = LocalDate.now();
        concluirEmprestimo.setData_devolucao(data_devolucao);

        emprestimoRepository.save(concluirEmprestimo);

        ExemplarId exemplarId = new ExemplarId(isbn_exemplar, codigo_exemplar);
        Exemplar exemplar = exemplarRepository.getReferenceById(exemplarId);
        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        exemplarRepository.save(exemplar);

        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }
}
