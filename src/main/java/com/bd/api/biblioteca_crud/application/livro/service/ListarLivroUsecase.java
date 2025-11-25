package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ListarLivroUsecase {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void execute(Model model){
        List<Livro> livros = livroRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();

        model.addAttribute("livros", livros);
        model.addAttribute("categorias", categorias);
    }
}
