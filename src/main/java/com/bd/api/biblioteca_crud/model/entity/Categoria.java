package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.LivroPertenceCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Categoria")
@Table(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LivroPertenceCategoria> livroPertenceCategoria;
}
