package com.bd.api.biblioteca_crud.domain.livro;

import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Livro_Pertence_Categoria")
@Table(name = "Livro_Pertence_Categoria")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LivroPertenceCategoria {

    @EmbeddedId
    @EqualsAndHashCode.Include
    protected LivroPertenceCategoriaId id;

    @ManyToOne
    @MapsId("isbn")
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    protected Livro livro;

    @ManyToOne
    @MapsId("categoria_id")
    @JoinColumn(name = "fk_categoria_id", nullable = false)
    protected Categoria categoria;

    public LivroPertenceCategoria(Livro livro, Categoria cat) {

        this(
            new LivroPertenceCategoriaId(livro.getIsbn(), cat.getId()),
            livro,
                cat
        );

    }
}
