package com.bd.api.biblioteca_crud.application.categoria.service;

import com.bd.api.biblioteca_crud.application.categoria.dto.request.CadastrarCategoriaDto;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarCategoriaUsecase {

    private final CategoriaRepository categoriaRepository;

    public CadastrarCategoriaUsecase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Categoria execute(CadastrarCategoriaDto dto) {
        categoriaRepository.findByNome(dto.nome()).ifPresent(c -> {
            throw new IllegalArgumentException("Categoria com o nome '" + dto.nome() + "' já existe.");
        });

        var categoria = new Categoria(dto.nome());
        return categoriaRepository.save(categoria);
    }
}