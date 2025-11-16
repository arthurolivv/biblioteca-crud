package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesativarLivroUsecase {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void desativar(Livro livro){
        livro.setDeleted(true);
        livroRepository.save(livro);
    }
}
