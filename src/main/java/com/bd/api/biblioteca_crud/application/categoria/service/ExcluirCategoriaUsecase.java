package com.bd.api.biblioteca_crud.application.categoria.service;

import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExcluirCategoriaUsecase {

    private final CategoriaRepository categoriaRepository;

    public ExcluirCategoriaUsecase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public void execute(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria com ID " + id + " não encontrada.");
        }

        categoriaRepository.deleteById(id);
    }
}