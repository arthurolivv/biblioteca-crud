package com.bd.api.biblioteca_crud.infraestructure.specification;

import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import org.springframework.data.jpa.domain.Specification;

public final class CategoriaSpecification {

    /**
     * Filtra categorias pelo nome (busca parcial case-insensitive).
     */
    public static Specification<Categoria> comNome(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return null; // Não aplica filtro
        }
        String likeBusca = "%" + busca.toLowerCase() + "%";
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("nome")), likeBusca);
    }
}