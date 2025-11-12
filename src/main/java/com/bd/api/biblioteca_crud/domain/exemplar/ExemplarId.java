package com.bd.api.biblioteca_crud.domain.exemplar;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExemplarId {

    private String livro_isbn;

    @Column(name = "codigo_exemplar", nullable = false)
    private String codigo_exemplar;
}
