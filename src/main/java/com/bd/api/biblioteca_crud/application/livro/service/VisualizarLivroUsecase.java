package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class VisualizarLivroUsecase {

    @Autowired
    private LivroRepository livroRepository;

    public void execute(Model model, String isbn){

        Livro livro = livroRepository.getReferenceById(isbn);
        model.addAttribute("livro", livro);
    }
}
