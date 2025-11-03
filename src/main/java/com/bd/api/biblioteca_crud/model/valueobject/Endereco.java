package com.bd.api.biblioteca_crud.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String país;

    private String cep;

}
