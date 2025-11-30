package com.bd.api.biblioteca_crud.infraestructure.specification;

import com.bd.api.biblioteca_crud.domain.editora.Editora;
import org.springframework.data.jpa.domain.Specification;

public final class EditoraSpecification {

    /**
     * Filtra editoras pelo CNPJ ou Razão Social (busca parcial case-insensitive).
     */
    public static Specification<Editora> comBusca(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return null; // Não aplica filtro
        }
        String likeBusca = "%" + busca.toLowerCase() + "%";
        return (root, query, builder) ->
                builder.or(
                        builder.like(builder.lower(root.get("cnpj")), likeBusca),
                        builder.like(builder.lower(root.get("razao_social")), likeBusca)
                );
    }
}