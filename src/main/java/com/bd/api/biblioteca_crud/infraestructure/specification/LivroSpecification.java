package com.bd.api.biblioteca_crud.infraestructure.specification;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.livro.LivroPertenceCategoria; // Entidade de relacionamento Categoria
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro; // Entidade de relacionamento Autor
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public final class LivroSpecification {

    // 1. Especificação para buscar por ISBN, Título ou Nome do Autor
    public static Specification<Livro> comBusca(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return null;
        }
        String likeBusca = "%" + busca.toLowerCase() + "%";
        return (root, query, builder) -> {
            query.distinct(true); // Evita duplicatas ao fazer join com coleções
            // Join com a entidade de relacionamento do Autor
            Join<Livro, AutorEscreveLivro> autorJoin = root.join("AutorEscreveLivro", JoinType.LEFT);

            return builder.or(
                    builder.like(builder.lower(root.get("isbn")), likeBusca),
                    builder.like(builder.lower(root.get("titulo")), likeBusca),
                    builder.like(builder.lower(autorJoin.get("autor").get("nome")), likeBusca)
            );
        };
    }

    // 2. Especificação para filtrar por ID de Categoria
    public static Specification<Livro> comCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        return (root, query, builder) -> {
            query.distinct(true);
            Join<Livro, LivroPertenceCategoria> categoriaJoin = root.join("livroPertenceCategoria");
            return builder.equal(categoriaJoin.get("categoria").get("id"), categoriaId);
        };
    }

    // 3. Especificação para filtrar por Autor (usando OPLID)
    public static Specification<Livro> comAutor(String autorOplid) {
        if (autorOplid == null || autorOplid.isEmpty()) {
            return null;
        }
        return (root, query, builder) -> {
            query.distinct(true);
            Join<Livro, AutorEscreveLivro> autorJoin = root.join("AutorEscreveLivro");
            return builder.equal(autorJoin.get("autor").get("oplid"), autorOplid);
        };
    }

    // 4. Especificação para filtrar por Idioma
    public static Specification<Livro> comIdioma(String idioma) {
        if (idioma == null || idioma.isEmpty()) {
            return null;
        }
        return (root, query, builder) -> builder.equal(
                root.get("idioma"),
                Enum.valueOf(Idioma.class, idioma)
        );
    }

    // 5. Especificação para filtrar por Status (ativo/inativo - baseado no campo 'deleted')
    public static Specification<Livro> comStatus(Boolean ativo) {
        if (ativo == null) {
            return null;
        }
        // Ativo (true) = deleted é false. Inativo (false) = deleted é true.
        return (root, query, builder) -> builder.equal(root.get("deleted"), !ativo);
    }
}