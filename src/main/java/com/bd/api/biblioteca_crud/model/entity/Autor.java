package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.model.valueobject.Nacionalidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Table(name = "Autor")
@Entity(name = "Autor")
@SQLRestriction("deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Autor {

    @Id
    @EqualsAndHashCode.Include
    @Column(name="id", length = 255)
    private String oplid;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "nacionalidade", nullable = false)
    private Nacionalidade nacionalidade;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AutorEscreveLivro> escreve;

    public void softDelete() {
        this.deleted = true;
    }
}
