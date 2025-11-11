package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        List<Livro> livros = livroRepository.findAll();
        model.addAttribute("livros", livros);
        return "livros/teste";
    }
}
