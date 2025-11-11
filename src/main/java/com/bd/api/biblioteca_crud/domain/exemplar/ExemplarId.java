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

    @Column(name = "numero_exemplar", nullable = false)
    private Long numero_exemplar;
}
