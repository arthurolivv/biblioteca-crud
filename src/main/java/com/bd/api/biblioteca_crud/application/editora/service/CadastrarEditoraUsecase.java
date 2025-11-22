package com.bd.api.biblioteca_crud.application.editora.service;

import com.bd.api.biblioteca_crud.application.editora.dto.request.CadastrarEditoraDto;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EditoraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEditoraUsecase {

    private final EditoraRepository repository;

    @Autowired
    public CadastrarEditoraUsecase(EditoraRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Editora execute(CadastrarEditoraDto dados) {
        if (repository.existsById(dados.cnpj())) {
            throw new IllegalArgumentException("Editora com CNPJ '" + dados.cnpj() + "' já cadastrada.");
        }

        Editora editora = new Editora(dados);
        repository.save(editora);
        return editora;
    }
}