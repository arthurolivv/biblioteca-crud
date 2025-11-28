package com.bd.api.biblioteca_crud.infraestructure.specification;

import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.shared.enums.Nacionalidade;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public final class AutorSpecification{

    /**
     * Filtra autores pelo nome (busca parcial case-insensitive).
     */
    public static Specification<Autor> comNome(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return null; // Não aplica filtro
        }
        String likeBusca = "%" + busca.toLowerCase() + "%";
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("nome")), likeBusca);
    }

    /**
     * Filtra autores pela nacionalidade exata.
     */
    public static Specification<Autor> comNacionalidade(String nacionalidade) {
        if (nacionalidade == null || nacionalidade.trim().isEmpty()) {
            return null; // Não aplica filtro
        }
        return (root, query, builder) -> {
            try {
                // Converte a String para o Enum Nacionalidade
                Nacionalidade nacEnum = Nacionalidade.valueOf(nacionalidade);
                Path<Nacionalidade> nacionalidadePath = root.get("nacionalidade");
                return builder.equal(nacionalidadePath, nacEnum);
            } catch (IllegalArgumentException e) {
                // Se a string for inválida, retorna uma condição que não encontra nada
                return builder.disjunction();
            }
        };
    }
}