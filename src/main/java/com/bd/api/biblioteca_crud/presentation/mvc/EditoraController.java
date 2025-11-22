package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.editora.dto.request.CadastrarEditoraDto;
import com.bd.api.biblioteca_crud.application.editora.service.CadastrarEditoraUsecase;
import com.bd.api.biblioteca_crud.application.editora.service.ExcluirEditoraUsecase;
import com.bd.api.biblioteca_crud.application.editora.service.ListarEditorasUsecase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editoras")
public class EditoraController {

    private final ListarEditorasUsecase listarEditorasUsecase;
    private final CadastrarEditoraUsecase cadastrarEditoraUsecase;
    private final ExcluirEditoraUsecase excluirEditoraUsecase;

    public EditoraController(
            ListarEditorasUsecase listarEditorasUsecase,
            CadastrarEditoraUsecase cadastrarEditoraUsecase,
            ExcluirEditoraUsecase excluirEditoraUsecase) {
        this.listarEditorasUsecase = listarEditorasUsecase;
        this.cadastrarEditoraUsecase = cadastrarEditoraUsecase;
        this.excluirEditoraUsecase = excluirEditoraUsecase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaDeEditoras", listarEditorasUsecase.execute());

        if (!model.containsAttribute("cadastrarEditoraDto")) {
            model.addAttribute("cadastrarEditoraDto", new CadastrarEditoraDto(null, null));
        }

        return "editora/lista";
    }

    @PostMapping
    public String cadastrar(
            @ModelAttribute("cadastrarEditoraDto") @Valid CadastrarEditoraDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cadastrarEditoraDto", result);
            redirectAttributes.addFlashAttribute("cadastrarEditoraDto", dto);
            return "redirect:/editoras";
        }

        try {
            cadastrarEditoraUsecase.execute(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Editora '" + dto.razaoSocial() + "' cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("cadastrarEditoraDto", dto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar editora.");
            redirectAttributes.addFlashAttribute("cadastrarEditoraDto", dto);
        }

        return "redirect:/editoras";
    }

    @PostMapping("/{cnpj}/excluir")
    public String excluir(@PathVariable String cnpj, RedirectAttributes redirectAttributes) {
        try {
            excluirEditoraUsecase.execute(cnpj);
            redirectAttributes.addFlashAttribute("successMessage", "Editora excluída com sucesso!");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            // Tratamento de ConstraintViolationException, similar a CategoriaController
            String message = e.getMessage();
            if (message != null && message.contains("ConstraintViolationException")) {
                redirectAttributes.addFlashAttribute("errorMessage", "Não foi possível excluir a editora, pois ela está associada a um ou mais livros.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir editora.");
            }
        }

        return "redirect:/editoras";
    }
}