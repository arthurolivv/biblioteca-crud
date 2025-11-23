package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController (AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/{oplid}")
    public String showVisualizarAutorPagina(@PathVariable String oplid, Model model) {
        Autor autor = autorRepository.findById(oplid)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        model.addAttribute("autor", autor);
        model.addAttribute("livros", autor.getEscreve());

        return "autor/visualizar";
    }

    @GetMapping({"", "/"})
    public String showListarAutorPagina(Model model){
        List<Autor> autores = autorRepository.findAll();
        model.addAttribute("autores", autores);
        return "autor/lista";

    }

}
