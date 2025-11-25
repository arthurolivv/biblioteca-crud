package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.categoria.dto.request.CadastrarCategoriaDto;
import com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto; // NOVO IMPORT
import com.bd.api.biblioteca_crud.application.categoria.service.CadastrarCategoriaUsecase;
import com.bd.api.biblioteca_crud.application.categoria.service.ExcluirCategoriaUsecase;
import com.bd.api.biblioteca_crud.application.categoria.service.ListarCategoriasUsecase;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final ListarCategoriasUsecase listarCategoriasUsecase;
    private final CadastrarCategoriaUsecase cadastrarCategoriaUsecase;
    private final ExcluirCategoriaUsecase excluirCategoriaUsecase;

    public CategoriaController(
            ListarCategoriasUsecase listarCategoriasUsecase,
            CadastrarCategoriaUsecase cadastrarCategoriaUsecase,
            ExcluirCategoriaUsecase excluirCategoriaUsecase) {
        this.listarCategoriasUsecase = listarCategoriasUsecase;
        this.cadastrarCategoriaUsecase = cadastrarCategoriaUsecase;
        this.excluirCategoriaUsecase = excluirCategoriaUsecase;
    }

    @GetMapping
    public String listar(Model model) {
        List<ListarCategoriaDto> categorias = listarCategoriasUsecase.execute();

        long totalLivrosCadastrados = categorias.stream()
                .mapToLong(ListarCategoriaDto::totalLivros)
                .sum();

        model.addAttribute("categorias", categorias);
        model.addAttribute("totalLivrosCadastrados", totalLivrosCadastrados); // NOVO ATRIBUTO

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
            return "redirect:/categoria";
        }

        try {
            cadastrarCategoriaUsecase.execute(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria '" + dto.nome() + "' cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("cadastrarCategoriaDto", dto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar categoria.");
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
                redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir categoria.");
            }
        }

        return "redirect:/categoria";
    }
}