package com.bd.api.biblioteca_crud.domain.reserva;


import com.bd.api.biblioteca_crud.domain.emprestimo.EmprestimoId;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Usuario_Reserva_Livro")
@Table(name = "Usuario_Reserva_Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioReservaLivro {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ReservaId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @Column(nullable = false)
    private LocalDate data_reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario_cpf", nullable = false)
    @MapsId("usuario_cpf")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    @MapsId("livro_isbn")
    private Livro livro;

}
