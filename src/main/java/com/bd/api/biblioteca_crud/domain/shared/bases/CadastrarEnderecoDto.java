package com.bd.api.biblioteca_crud.domain.shared.bases;

import jakarta.validation.constraints.NotBlank;

public record CadastrarEnderecoDto(
        @NotBlank(message = "Rua é um campo obrigatório")
        String rua,
        @NotBlank(message = "Número é um campo obrigatório")
        String numero,
        @NotBlank(message = "Se não houver, informe 'N/A'")
        String complemento,
        @NotBlank(message = "Bairro é um campo obrigatório")
        String bairro,
        @NotBlank(message = "Cidade é um campo obrigatório")
        String cidade,
        @NotBlank(message = "Estado é um campo obrigatório")
        String estado,
        @NotBlank(message = "CEP é um campo obrigatório")
        String cep,
        @NotBlank(message = "País é um campo obrigatório")
        String pais
) {
}
