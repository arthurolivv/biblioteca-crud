package com.bd.api.biblioteca_crud.domain.livro;

import com.bd.api.biblioteca_crud.domain.biblioteca.Biblioteca;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.Year;
import java.util.List;

@Entity(name = "Livro")
@Table(name = "Livro")
@SQLRestriction("deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Year ano_publicacao;

    @Column(nullable = false)
    private Short disponiveis;

    @Column(nullable = false)
    private Short quantidade;

    @Column(nullable = false)
    private String sinopse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Idioma idioma;

    @Column(nullable = false)
    private String imagem_url;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne(optional = false) //minimo 1 livro
    @JoinColumn(name = "fk_editora_cnpj", nullable = false)
    private Editora editora;

    @ManyToOne(optional = false) //minimo 1 livro
    @JoinColumn(name = "fk_biblioteca_cnpj")
    private Biblioteca biblioteca;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LivroPertenceCategoria> livroPertenceCategoria;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AutorEscreveLivro> AutorEscreveLivro;

    @OneToMany(mappedBy = "livro", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UsuarioReservaLivro> reservas;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exemplar> exemplares;

    public void softDelete() {
        this.deleted = true;
    }

}
