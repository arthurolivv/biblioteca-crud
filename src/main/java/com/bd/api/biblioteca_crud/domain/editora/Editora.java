package com.bd.api.biblioteca_crud.domain.editora;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity(name = "Editora")
@Table(name = "Editora")
@SQLRestriction("deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Editora {

    @Id
    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razao_social;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEditora> enderecos;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Livro> livros;

    public void softDelete() {
        this.deleted = true;
    }

}
