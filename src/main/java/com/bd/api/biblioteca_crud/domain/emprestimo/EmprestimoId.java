package com.bd.api.biblioteca_crud.domain.emprestimo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmprestimoId {

    @Column(name = "num_emprestimo", nullable = false)
    private Long num_emprestimo;

    private String usuarioCpf;
}
