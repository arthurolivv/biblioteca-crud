package com.bd.api.biblioteca_crud.domain.livro;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class LivroPertenceCategoriaId {

    private String isbn;

    private long categoria_id;
}
