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

    private LocalDateTime data_emprestimo;

    private LocalDateTime data_devolucao;

    private LocalDateTime data_devolucao_prevista;

    private String livro_isbn;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_cpf")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_exemplar_id")
    private Exemplar exemplar;
}
