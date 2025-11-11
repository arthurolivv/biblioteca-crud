package com.bd.api.biblioteca_crud.domain.autor;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AutorEscreveLivroId implements Serializable {

    private String oplid;

    private String isbn;
}
