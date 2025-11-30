package com.bd.api.biblioteca_crud.application.editora.service;

import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarEditorasUsecase {

    private final EditoraRepository editoraRepository;

    public List<Editora> execute(String ordem, String busca) {
        List<Editora> editoras;

        // Se a ordenação for por quantidade de livros, usa queries especiais
        if ("livros_desc".equals(ordem)) {
            editoras = editoraRepository.findAllOrderByLivroCountDesc();
        } else if ("livros_asc".equals(ordem)) {
            editoras = editoraRepository.findAllOrderByLivroCountAsc();
        } else {
            // Busca todas as editoras sem ordenação específica
            editoras = editoraRepository.findAll();
        }

        // Aplica filtro de busca se fornecido
        if (busca != null && !busca.trim().isEmpty()) {
            String buscaLower = busca.toLowerCase().trim();
            editoras = editoras.stream()
                    .filter(e -> e.getCnpj().toLowerCase().contains(buscaLower) ||
                            e.getRazao_social().toLowerCase().contains(buscaLower))
                    .collect(Collectors.toList());
        }

        // Aplica ordenação manual por razão social
        if ("razao_desc".equals(ordem)) {
            editoras = editoras.stream()
                    .sorted(Comparator.comparing(Editora::getRazao_social,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)).reversed())
                    .collect(Collectors.toList());
        } else if ("razao_asc".equals(ordem) || ordem == null) {
            editoras = editoras.stream()
                    .sorted(Comparator.comparing(Editora::getRazao_social,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                    .collect(Collectors.toList());
        }

        return editoras;
    }
}