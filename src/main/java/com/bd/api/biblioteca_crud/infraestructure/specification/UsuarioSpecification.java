package com.bd.api.biblioteca_crud.infraestructure.specification;

import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe para construir critérios de pesquisa (WHERE clauses) dinâmicos
 * usando JPA Criteria API.
 */
public class UsuarioSpecification {

    /**
     * Filtra usuários por busca em nome, CPF ou e-mail.
     */
    public static Specification<Usuario> comBusca(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return null;
        }
        String likePattern = "%" + busca.toLowerCase() + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome").get("priNome")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("cpf")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likePattern)
        );
    }

    /**
     * Filtra usuários pelo status (ativo/inativo), usando o campo 'deleted'.
     * 'status' é a string ("true" ou "false") enviada pelo HTML.
     */
    public static Specification<Usuario> comStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return null;
        }

        // Converte a string "true" (Inativo) ou "false" (Ativo) para o booleano do campo 'deleted'
        final boolean isDeleted = Boolean.parseBoolean(status);

        // A query será WHERE deleted = isDeleted
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), isDeleted);
    }

    /**
     * Filtra usuários por categoria.
     */
    public static Specification<Usuario> comCategoria(String categoriaId) {
        if (categoriaId == null || categoriaId.trim().isEmpty()) {
            return null;
        }

        try {
            Long id = Long.parseLong(categoriaId);
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("categoria").get("id"), id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}