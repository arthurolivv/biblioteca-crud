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

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private CadastrarLivroValidationService validar;


    @GetMapping({"", "/"})
    public String showListarLivroPagina(Model model) {

        List<Livro> livros = livroRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();

        model.addAttribute("livros", livros);
        model.addAttribute("categorias", categorias);

        return "livros/lista";
    }

    @GetMapping("/cadastro")
    public String showCadastrarLivroPagina(Model model) {

        CadastrarLivroDto cadastrarLivroDto = new CadastrarLivroDto(
                "", // isbn
                "", // titulo
                null, // ano_publicacao
                List.of(), // autor
                List.of(), // categoria_id
                "", // imagem_url
                null, // idioma
                "", // editora_cnpj
                List.of(), // exemplares
                "" //sinopse
        ); //criando dto vazio apenas para poder inicializa-lo

        model.addAttribute("cadastrarLivroDto", cadastrarLivroDto);
        carregarDadosFormulario(model);

        return "livros/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarLivro(
            @Valid @ModelAttribute CadastrarLivroDto dto,
            BindingResult result,
            Model model) {

        validar.execute(dto, result);

        if (result.hasErrors()) {
            carregarDadosFormulario(model);
            return "livros/cadastro";
        }

        cadastrar.execute(dto);

        return "redirect:/livros";
    }

    @GetMapping("/editar")
    public String showEditarLivroPagina(Model model, @RequestParam String isbn) {


        try {
            Livro livro = livroRepository.getReferenceById(isbn);
            model.addAttribute("livro", livro);

            carregarDadosFormulario(model);

            EditarLivroDto editarLivroDto = new EditarLivroDto(livro);

            model.addAttribute("editarLivroDto", editarLivroDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            carregarDadosFormulario(model);
            return "redirect:/livros";
        }


        return "livros/editar";
    }

    @PostMapping("/editar")
    public String editarLivro(
            Model model,
            @RequestParam String isbn,
            @Valid @ModelAttribute EditarLivroDto dto,
            BindingResult result
    ) {
        try {
            Livro livro = livroRepository.findById(isbn).get();
            model.addAttribute("livro", livro);

            if (result.hasErrors()) {
                return "livros/editar";
            }

            editar.execute(dto, isbn);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            carregarDadosFormulario(model);
        }
        return "redirect:/livros";
    }

    @GetMapping("/visualizar")
    public String showVisualizarLivroPagina(Model model, @RequestParam String isbn) {

        try {
            Livro livro = livroRepository.findById(isbn).get();
            model.addAttribute("livro", livro);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/livros";
        }

        return "livros/visualizar";
    }

    @GetMapping("/desativar")
    public String desativarLivro(@RequestParam String isbn) {

        try {
            Livro livro = livroRepository.getReferenceById(isbn);
            desativar.execute(livro);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    @GetMapping("/ativar")
    public String ativarLivro(@RequestParam String isbn) {

        try {
            Livro livro = livroRepository.getReferenceById(isbn);
            ativar.execute(livro);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    private void carregarDadosFormulario(Model model) {

        List<Autor> autores = autorRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();
        List<Editora> editoras = editoraRepository.findAll();

        model.addAttribute("idiomas", Idioma.values());
        model.addAttribute("autores", autores);
        model.addAttribute("categorias", categorias);
        model.addAttribute("editoras", editoras);
    }

}
