package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.AutorEscreveLivro;
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
    @Column(name="id")
    private String oplid;

    private String nome;

    private String nacionalidade;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AutorEscreveLivro> escreve;

    public void softDelete() {
        this.deleted = true;
    }

}
