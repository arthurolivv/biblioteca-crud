package com.bd.api.biblioteca_crud.domain.emprestimo;

import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Usuario_Emprestimo_Exemplar")
@Table(name = "Usuario_Emprestimo_Exemplar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioEmprestimoExemplar {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private EmprestimoId id;

    @Column(nullable = false)
    private LocalDateTime data_emprestimo;

    @Column(nullable = false)
    private LocalDateTime data_devolucao;

    @Column(nullable = false)
    private LocalDateTime data_devolucao_prevista;

    @Column(nullable = false)
    private String livro_isbn;

    @ManyToOne
    @MapsId("usuarioCpf")
    @JoinColumn(name = "fk_usuario_cpf", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "fk_livro_isbn", referencedColumnName = "fk_livro_isbn", nullable = false),
            @JoinColumn(name = "fk_exemplar_numero_exemplar", referencedColumnName = "numero_exemplar", nullable = false)
    })
    private Exemplar exemplar;

    @PrePersist
    public void gerarNumeroEmprestimo() {
        if (id == null || id.getNum_emprestimo() == null) {
            long proximo = (usuario.getEmprestimos() == null) ? 1 : usuario.getEmprestimos().size() + 1;
            this.id = new EmprestimoId(proximo, usuario.getCpf());
        }
    }
}
