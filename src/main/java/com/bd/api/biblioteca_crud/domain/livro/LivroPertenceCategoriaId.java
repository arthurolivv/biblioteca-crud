package com.bd.api.biblioteca_crud.domain.livro;

import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LivroPertenceCategoriaId {

    private String isbn;

    private long categoria_id;
}
