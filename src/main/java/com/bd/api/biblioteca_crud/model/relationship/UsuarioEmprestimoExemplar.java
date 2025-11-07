package com.bd.api.biblioteca_crud.model.relationship;

import com.bd.api.biblioteca_crud.model.entity.Exemplar;
import com.bd.api.biblioteca_crud.model.entity.Livro;
import com.bd.api.biblioteca_crud.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Usuario_Emprestimo_Exemplar")
@Table(name = "Usuario_Emprestimo_Exemplar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioEmprestimoExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data_emprestimo;

    @Column(nullable = false)
    private LocalDateTime data_devolucao;

    @Column(nullable = false)
    private LocalDateTime data_devolucao_prevista;

    @Column(nullable = false)
    private String livro_isbn;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_cpf", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "fk_livro_isbn", referencedColumnName = "isbn", nullable = false),
            @JoinColumn(name = "fk_exemplar_numero_exemplar", referencedColumnName = "numero_exemplar", nullable = false)
    })
    private Exemplar exemplar;
}
