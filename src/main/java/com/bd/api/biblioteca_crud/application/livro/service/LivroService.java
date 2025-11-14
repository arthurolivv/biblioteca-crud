package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;

//    public List<Livro> filtrar(String busca, String ordem, Long categoriaId, Boolean status) {
//
//        var livros = livroRepository.findAll();
//    }
}
