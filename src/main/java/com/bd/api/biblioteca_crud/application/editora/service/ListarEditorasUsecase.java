package com.bd.api.biblioteca_crud.application.editora.service;

import com.bd.api.biblioteca_crud.application.editora.dto.response.ListarEditoraDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ListarEditorasUsecase {

    private final EditoraRepository repository;

    @Autowired
    public ListarEditorasUsecase(EditoraRepository repository) {
        this.repository = repository;
    }

    public List<ListarEditoraDto> execute() {

        List<ListarEditoraDto> editoras = repository.findAllWithLivros().stream().map(ListarEditoraDto::new).toList();

        return editoras.stream()
                .sorted(Comparator.comparing(ListarEditoraDto::razaoSocial))
                .toList();
    }
}