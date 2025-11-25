package com.bd.api.biblioteca_crud.application.categoria.service;

import com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCategoriasUsecase {

    private final CategoriaRepository categoriaRepository;

    public ListarCategoriasUsecase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<ListarCategoriaDto> execute() {
        return categoriaRepository.findAllCategoriasWithLivrosCount();
    }
}