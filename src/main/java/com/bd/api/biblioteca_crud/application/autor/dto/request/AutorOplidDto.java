package com.bd.api.biblioteca_crud.application.autor.dto.request;

import jakarta.validation.constraints.Pattern;

public record AutorOplidDto(

        @Pattern(
                regexp = "^OL\\d{7,10}+A$",
                message = "Oplid do autor deve começar com 'OL', terminar com 'A' e ter apenas números no meio (10 ou 13 caracteres)"
        )
        String oplid
) {
}
