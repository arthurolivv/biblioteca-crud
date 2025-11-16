package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.service.CadastrarLivroUsecase;
import com.bd.api.biblioteca_crud.application.livro.service.CadastrarLivroValidationService;
import com.bd.api.biblioteca_crud.application.livro.service.EditarLivroUsecase;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private CadastrarLivroUsecase cadastrarLivroUsecase;

    @Autowired
    private EditarLivroUsecase editarLivroUsecase;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private CadastrarLivroValidationService cadastrarLivroValidationServices;


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

        cadastrarLivroValidationServices.validate(dto, result);

        if (result.hasErrors()) {
            carregarDadosFormulario(model);
            return "livros/cadastro";
        }

        cadastrarLivroUsecase.cadastrarLivro(dto);

        return "redirect:/livros";
    }

    @GetMapping("/editar")
    public String showEditarLivroPagina(Model model, @RequestParam String isbn) {


        try{
            Livro livro = livroRepository.findById(isbn).get();
            model.addAttribute("livro", livro);

            carregarDadosFormulario(model);

            EditarLivroDto editarLivroDto = new EditarLivroDto(livro);

            model.addAttribute("editarLivroDto", editarLivroDto);

        }
        catch (Exception e){
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
    ){
        try {
            Livro livro = livroRepository.findById(isbn).get();
            model.addAttribute("livro", livro);

            if(result.hasErrors()){
                return "livros/editar";
            }

            editarLivroUsecase.editarLivro(dto, isbn);
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            carregarDadosFormulario(model);
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
