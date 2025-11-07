package com.bd.api.biblioteca_crud.model.relationship;

import com.bd.api.biblioteca_crud.model.entity.Categoria;
import com.bd.api.biblioteca_crud.model.entity.Livro;
import com.bd.api.biblioteca_crud.model.valueobject.LivroPertenceCategoriaId;
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
    private LivroPertenceCategoriaId id;

    @ManyToOne
    @MapsId("isbn")
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    private Livro livro;

    @ManyToOne
    @MapsId("categoria_id")
    @JoinColumn(name = "fk_categoria_id", nullable = false)
    private Categoria categoria;
}
