package com.bd.api.biblioteca_crud.model.relationship;


import com.bd.api.biblioteca_crud.model.entity.Livro;
import com.bd.api.biblioteca_crud.model.entity.Usuario;
import com.bd.api.biblioteca_crud.model.valueobject.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Usuario_Reserva_Livro")
@Table(name = "Usuario_Reserva_Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioReservaLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @Column(nullable = false)
    private LocalDateTime data_reserva;

    @Column(nullable = false)
    private Short posicao_fila;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario_cpf", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_livro_isbn", nullable = false)
    private Livro livro;

}
