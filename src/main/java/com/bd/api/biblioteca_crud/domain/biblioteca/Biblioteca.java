package com.bd.api.biblioteca_crud.domain.biblioteca;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.bases.Endereco;
import jakarta.persistence.*;
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
