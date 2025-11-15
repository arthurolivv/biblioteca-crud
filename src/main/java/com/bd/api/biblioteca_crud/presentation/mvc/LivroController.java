package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.service.LivroService;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping({"", "/"})
    public String listar(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String ordem,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Boolean status,
            Model model) {

        List<Livro> livros = livroRepository.findAll();

        if (busca != null && !busca.isBlank()) {
            livros = livroRepository.findByTituloContainingIgnoreCase(busca);
        }
        if (ordem != null) {
            livros = ordenarLivros(ordem);
        }
        if (categoriaId != null) {
            livros = livroRepository.findByCategoria(categoriaId);
        }
        if (status != null) {
            livros = livroRepository.findByStatus(status);
        }

        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("livros", livros);
        model.addAttribute("categorias", categorias);

        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("ordenar", ordem);
        model.addAttribute("busca", busca);
        model.addAttribute("status", status);

        return "listagem/livros";
    }

//    @GetMapping("/adicionar")
//    public String(Livro livro) {
//
//
//        return "adicionar/livros";
//    }

    private List<Livro> ordenarLivros(String criterio) {
        switch (criterio) {
            case "titulo_asc":
                return livroRepository.findAllByOrderByTituloAsc();
            case "titulo_desc":
                return livroRepository.findAllByOrderByTituloDesc();
            default:
                return livroRepository.findAll();
        }
    }
}
