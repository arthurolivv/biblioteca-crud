package com.bd.api.biblioteca_crud.application.editora.service;

import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EditoraRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirEditoraUsecase {

    private final EditoraRepository repository;

    @Autowired
    public ExcluirEditoraUsecase(EditoraRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(String cnpj) {
        if (!repository.existsById(cnpj)) {
            throw new EntityNotFoundException("Editora com CNPJ '" + cnpj + "' não encontrada ou já excluída.");
        }

        Editora editora = repository.getReferenceById(cnpj);
        editora.softDelete();
    }
}