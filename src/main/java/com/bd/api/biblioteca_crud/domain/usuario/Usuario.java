package com.bd.api.biblioteca_crud.domain.usuario;

import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.emprestimo.UsuarioEmprestimoExemplar;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.bases.Endereco;
import com.bd.api.biblioteca_crud.domain.shared.bases.Nome;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "Usuario")
//@SQLRestriction("deleted = false")
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

    @Column
    private Endereco endereco;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @OneToMany(mappedBy = "usuario", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UsuarioReservaLivro> reservas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioEmprestimoExemplar> emprestimos;

    public Usuario(CadastrarUsuarioDto dto, String senhaCriptografada) {
        this.cpf = dto.cpf();
        this.rg = dto.rg();
        this.email = dto.email();
        this.senha = senhaCriptografada;
        this.nome = new Nome(dto.nome().pri_nome(), dto.nome().sob_nome());
        this.data_nasc = dto.data_nasc();
        this.endereco = new Endereco(
                dto.endereco().rua(),
                dto.endereco().numero(),
                dto.endereco().complemento(),
                dto.endereco().bairro(),
                dto.endereco().cidade(),
                dto.endereco().estado(),
                dto.endereco().cep(),
                dto.endereco().pais()
        );
        this.deleted = false;
    }

    public boolean verificarSenha(String senha, BCryptPasswordEncoder encoder) {
        return encoder.matches(senha, this.senha);
    }
}
