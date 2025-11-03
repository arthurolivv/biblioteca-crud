package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.UsuarioEmprestimoExemplar;
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

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "exemplar-id-generator")
    @GenericGenerator(name = "exemplar-id-generator", strategy = "com.bd.api.biblioteca_crud.util.ExemplarIdGenerator")
    @Column(length = 10, nullable = false, unique = true, updatable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusExemplar status;

    @Column(nullable = false)
    private Boolean proprio; //indica se o livro é fixo na biblioteca
    //se TRUE, então não pode ser emprestado. disponiveis = quantidade - quantidade de proprios

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_livro_oplid", nullable = false)
    private Livro livro;

    @OneToMany(mappedBy = "exemplar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioEmprestimoExemplar> emprestimos;


}
