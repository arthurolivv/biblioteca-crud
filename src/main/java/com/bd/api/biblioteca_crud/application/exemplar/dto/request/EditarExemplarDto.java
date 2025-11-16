package com.bd.api.biblioteca_crud.application.exemplar.dto.request;

import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EditarExemplarDto (

    @NotBlank(message = "Informe o Código de Exemplar")
    @Pattern(
            regexp = "^EX-\\d+$",
            message = "O código de um exemplar deve seguir a seguinte formatação: EX-0000..."
    )
    String codigo_exemplar,

    //status virao padrao como disponiveis pois o livro acabou de ser cadastrado

    @NotNull(message = "Informe se o exemplar é exclusivo para consulta local")
    Boolean proprio,

    @NotNull
    StatusExemplar status
){
}
