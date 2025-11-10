package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.model.valueobject.ExemplarId;
import com.bd.api.biblioteca_crud.model.valueobject.StatusExemplar;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity(name = "Exemplar")
@Table(name = "Exemplar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Exemplar {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ExemplarId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusExemplar status;

    @Column(nullable = false)
    private Boolean proprio; //indica se o livro é fixo na biblioteca
    //se TRUE, então não pode ser emprestado. disponiveis = quantidade - quantidade de proprios

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    private Livro livro;

    @OneToMany(mappedBy = "exemplar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioEmprestimoExemplar> emprestimos;

    @PrePersist
    public void gerarNumeroExemplar() {
        if (id == null || id.getNumero_exemplar() == null) {
            long proximo = (livro.getExemplares() == null) ? 1 : livro.getExemplares().size() + 1;
            this.id = new ExemplarId(livro.getIsbn(), proximo);
        }
    }

}
