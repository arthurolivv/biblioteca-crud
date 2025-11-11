package com.bd.api.biblioteca_crud.domain.autor;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Autor_Escreve_Livro")
@Table(name = "Autor_Escreve_Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AutorEscreveLivro {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AutorEscreveLivroId id;

    @ManyToOne
    @MapsId("oplid")
    @JoinColumn(name = "fk_autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @MapsId("isbn")
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    private Livro livro;

}
