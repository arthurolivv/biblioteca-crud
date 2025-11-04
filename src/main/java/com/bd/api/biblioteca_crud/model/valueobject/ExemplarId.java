package com.bd.api.biblioteca_crud.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExemplarId {

    private String isbn;
    private Long numero_exemplar;
}
