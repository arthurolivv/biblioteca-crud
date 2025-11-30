package com.bd.api.biblioteca_crud.application.categoria.service;

import com.bd.api.biblioteca_crud.application.categoria.dto.response.ListarCategoriaDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarCategoriasUsecase {

    private final CategoriaRepository categoriaRepository;

    public List<ListarCategoriaDto> execute(String ordem, String busca) {
        List<ListarCategoriaDto> categorias;

        // Se a ordenação for por quantidade de livros, usa queries especiais
        if ("livros_desc".equals(ordem)) {
            categorias = categoriaRepository.findAllOrderByLivroCountDesc();
        } else if ("livros_asc".equals(ordem)) {
            categorias = categoriaRepository.findAllOrderByLivroCountAsc();
        } else {
            // Busca todas as categorias
            categorias = categoriaRepository.findAllCategoriasWithLivrosCount();
        }

        // Aplica filtro de busca se fornecido
        if (busca != null && !busca.trim().isEmpty()) {
            String buscaLower = busca.toLowerCase().trim();
            categorias = categorias.stream()
                    .filter(c -> c.nome().toLowerCase().contains(buscaLower))
                    .collect(Collectors.toList());
        }

        if ("nome_desc".equals(ordem)) {
            categorias = categorias.stream()
                    .sorted(Comparator.comparing(ListarCategoriaDto::nome,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)).reversed())
                    .collect(Collectors.toList());
        } else if ("nome_asc".equals(ordem)) {
            categorias = categorias.stream()
                    .sorted(Comparator.comparing(ListarCategoriaDto::nome,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                    .collect(Collectors.toList());
        }

        return categorias;
    }
}