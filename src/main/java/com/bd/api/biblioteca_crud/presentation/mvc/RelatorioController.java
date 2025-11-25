package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.relatorio.dto.response.*;
import com.bd.api.biblioteca_crud.application.relatorio.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping({"", "/"})
    public String showRelatoriosDashboard(Model model) {

        // empréstimos por categoria
        List<RelatorioCategoriaDto> emprestimosPorCategoria = relatorioService.getEmprestimosPorCategoria();
        model.addAttribute("emprestimosPorCategoria", emprestimosPorCategoria);

        // top 10 editoras
        List<RelatorioEditoraDto> top10Editoras = relatorioService.getTop10EditorasMaisEmprestadas();
        model.addAttribute("top10Editoras", top10Editoras);

        // top 10 autores
        List<RelatorioAutorDto> top10Autores = relatorioService.getTop10AutoresMaisEmprestados();
        model.addAttribute("top10Autores", top10Autores);

        // top 10 livros
        List<RelatorioLivroDto> top10Livros = relatorioService.getTop10LivrosMaisEmprestados();
        model.addAttribute("top10Livros", top10Livros);

        // emprestimo por status
        List<RelatorioStatusEmprestimoDto> emprestimosPorStatus = relatorioService.getEmprestimosPorStatus();
        model.addAttribute("emprestimosPorStatus", emprestimosPorStatus);

        return "relatorios/dashboard";
    }
}