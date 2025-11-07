package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.valueobject.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Table(name = "Biblioteca")
@Entity(name = "Biblioteca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Biblioteca {

    @Id
    @EqualsAndHashCode.Include
    private String cnpj;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "biblioteca", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros;
}
