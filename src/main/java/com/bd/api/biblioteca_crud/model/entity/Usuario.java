package com.bd.api.biblioteca_crud.model.entity;

import com.bd.api.biblioteca_crud.model.relationship.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.model.relationship.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.model.valueobject.Nome;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "Usuario")
@SQLRestriction("deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    private String cpf;

    @Column(nullable = false, unique = true)
    private String rg;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 60)
    private String senha;

    @Column(nullable = false)
    @Embedded
    private Nome nome;

    @Column(nullable = false)
    private LocalDate data_nasc;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "usuario", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UsuarioReservaLivro> reservas;

    public boolean verificarSenha(String senha, BCryptPasswordEncoder encoder) {
        return encoder.matches(senha, this.senha);
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioEmprestimoExemplar> emprestimos;

    public void softDelete() {
        this.deleted = true;
    }
}
