package com.bd.api.biblioteca_crud.domain.reserva;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReservaId {

    @Column(nullable = false)
    private String livro_isbn;

    @Column(nullable = false)
    private String usuario_cpf;

    @Column(nullable = false)
    private UUID num_reserva;

}
