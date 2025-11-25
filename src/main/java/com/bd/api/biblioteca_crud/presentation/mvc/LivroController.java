package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.service.*;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroUsecase cadastrar;
    private final EditarLivroUsecase editar;
    private final DesativarLivroUsecase desativar;
    private final AtivarLivroUsecase ativar;
    private final ListarLivroUsecase listar;
    private final VisualizarLivroUsecase visualizar;
    private final CadastrarLivroValidationService validar;


    @GetMapping({"", "/"})
    public String showListarLivroPagina(Model model) {

        listar.execute(model);
        return "livros/lista";
    }

    @GetMapping("/cadastro")
    public String showCadastrarLivroPagina(Model model) {

        cadastrar.show(model);
        return "livros/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarLivro(
            @Valid @ModelAttribute CadastrarLivroDto dto,
            BindingResult result) {

        validar.execute(dto, result);

        if (result.hasErrors()) {
            return "livros/cadastro";
        }

        cadastrar.execute(dto);

        return "redirect:/livros";
    }

    @GetMapping("/editar")
    public String showEditarLivroPagina(Model model, @RequestParam String isbn) {

        try {
            editar.show(model, isbn);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/livros";
        }

        return "livros/editar";
    }

    @PostMapping("/editar")
    public String editarLivro(
            @RequestParam String isbn,
            @Valid @ModelAttribute EditarLivroDto dto
    ) {
        try {
            editar.execute(dto, isbn);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "livros/editar";
        }
        return "redirect:/livros";
    }

    @GetMapping("/visualizar")
    public String showVisualizarLivroPagina(Model model, @RequestParam String isbn) {

        try {
            visualizar.execute(model, isbn);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/livros";
        }
        return "livros/visualizar";
    }

    @GetMapping("/desativar")
    public String desativarLivro(@RequestParam String isbn) {

        desativar.execute(isbn);
        return "redirect:/livros";
    }

    @GetMapping("/ativar")
    public String ativarLivro(@RequestParam String isbn) {

        ativar.execute(isbn);
        return "redirect:/livros";
    }

}
