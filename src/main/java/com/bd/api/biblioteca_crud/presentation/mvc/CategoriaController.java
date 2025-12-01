package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.categoria.dto.request.CadastrarCategoriaDto;
import com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto;
import com.bd.api.biblioteca_crud.application.categoria.service.CadastrarCategoriaUsecase;
import com.bd.api.biblioteca_crud.application.categoria.service.ExcluirCategoriaUsecase;
import com.bd.api.biblioteca_crud.application.categoria.service.ListarCategoriasUsecase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final ListarCategoriasUsecase listarCategoriasUsecase;
    private final CadastrarCategoriaUsecase cadastrarCategoriaUsecase;
    private final ExcluirCategoriaUsecase excluirCategoriaUsecase;

    @GetMapping
    public String listar(
            @RequestParam(required = false, defaultValue = "nome_desc") String ordem,
            @RequestParam(required = false, defaultValue = "") String busca,
            Model model) {

        try {
            List<ListarCategoriaDto> categorias = listarCategoriasUsecase.execute(ordem, busca);

            long totalLivrosCadastrados = categorias.stream()
                    .mapToLong(ListarCategoriaDto::totalLivros)
                    .sum();

            model.addAttribute("categorias", categorias);
            model.addAttribute("totalLivrosCadastrados", totalLivrosCadastrados);
        } catch (Exception e) {
            model.addAttribute("categorias", java.util.Collections.emptyList());
            model.addAttribute("totalLivrosCadastrados", 0L);
            model.addAttribute("errorMessage", "Erro ao carregar a lista de categorias: " + e.getMessage());
        }

        model.addAttribute("ordemSelecionada", ordem);
        model.addAttribute("buscaSelecionada", busca);

        if (!model.containsAttribute("cadastrarCategoriaDto")) {
            model.addAttribute("cadastrarCategoriaDto", new CadastrarCategoriaDto(null));
        }

        return "categoria/lista";
    }

    @PostMapping
    public String cadastrar(
            @ModelAttribute("cadastrarCategoriaDto") @Valid CadastrarCategoriaDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cadastrarCategoriaDto", result);
            redirectAttributes.addFlashAttribute("cadastrarCategoriaDto", dto);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro de validação! Verifique os campos.");
            return "redirect:/categoria";
        }

        try {
            cadastrarCategoriaUsecase.execute(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria '" + dto.nome() + "' cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("cadastrarCategoriaDto", dto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar categoria: " + e.getMessage());
            redirectAttributes.addFlashAttribute("cadastrarCategoriaDto", dto);
        }

        return "redirect:/categoria";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            excluirCategoriaUsecase.execute(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria excluída com sucesso!");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null && message.contains("ConstraintViolationException")) {
                redirectAttributes.addFlashAttribute("errorMessage", "Não foi possível excluir a categoria, pois ela está associada a um ou mais livros.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir categoria: " + e.getMessage());
            }
        }

        return "redirect:/categoria";
    }
}