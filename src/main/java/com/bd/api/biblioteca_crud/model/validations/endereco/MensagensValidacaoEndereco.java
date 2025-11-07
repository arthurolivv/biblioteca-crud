package com.bd.api.biblioteca_crud.model.validations.endereco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public final class MensagensValidacaoEndereco {

    public static final String RUA_VALIDACAO_MENSAGEM = campoObrigatorio("Rua");
    public static final String NUMERO_VALIDACAO_MENSAGEM = campoObrigatorio("Número");
    public static final String COMPLEMENTO_VALIDACAO_MENSAGEM = campoObrigatorio("Complemento");
    public static final String BAIRRO_VALIDACAO_MENSAGEM = campoObrigatorio("Bairro");
    public static final String CIDADE_VALIDACAO_MENSAGEM = campoObrigatorio("Cidade");
    public static final String ESTADO_VALIDACAO_MENSAGEM = campoObrigatorio("Estado");
    public static final String PAIS_VALIDACAO_MENSAGEM = campoObrigatorio("País");
    public static final String CEP_VALIDACAO_MENSAGEM = campoObrigatorio("CEP");

    private static String campoObrigatorio(String campo) {
        return "Campo '" + campo + "' não está preenchido.";
    }
}
