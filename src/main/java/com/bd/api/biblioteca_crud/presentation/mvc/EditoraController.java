package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.editora.dto.request.CadastrarEditoraDto;
import com.bd.api.biblioteca_crud.application.editora.service.CadastrarEditoraUsecase;
import com.bd.api.biblioteca_crud.application.editora.service.ExcluirEditoraUsecase;
import com.bd.api.biblioteca_crud.application.editora.service.ListarEditorasUsecase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editoras")
@RequiredArgsConstructor
public class EditoraController {

    private final ListarEditorasUsecase listarEditorasUsecase;
    private final CadastrarEditoraUsecase cadastrarEditoraUsecase;
    private final ExcluirEditoraUsecase excluirEditoraUsecase;

    @GetMapping
    public String listar(
            @RequestParam(required = false, defaultValue = "razao_asc") String ordem,
            @RequestParam(required = false, defaultValue = "") String busca,
            Model model) {

        try {
            model.addAttribute("listaDeEditoras", listarEditorasUsecase.execute(ordem, busca));
        } catch (Exception e) {
            model.addAttribute("listaDeEditoras", java.util.Collections.emptyList());
            model.addAttribute("errorMessage", "Erro ao carregar a lista de editoras: " + e.getMessage());
        }

        // SEMPRE adicionar esses atributos para evitar erros no template
        model.addAttribute("ordemSelecionada", ordem);
        model.addAttribute("buscaSelecionada", busca);

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
            redirectAttributes.addFlashAttribute("errorMessage", "Erro de validação! Verifique os campos.");
            return "redirect:/editoras";
        }

        try {
            cadastrarEditoraUsecase.execute(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Editora '" + dto.razaoSocial() + "' cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("cadastrarEditoraDto", dto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar editora: " + e.getMessage());
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
            String message = e.getMessage();
            if (message != null && message.contains("ConstraintViolationException")) {
                redirectAttributes.addFlashAttribute("errorMessage", "Não foi possível excluir a editora, pois ela está associada a um ou mais livros.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir editora: " + e.getMessage());
            }
        }

        return "redirect:/editoras";
    }
}