package com.bd.api.biblioteca_crud.model.valueobject;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.security.SecureRandom;

public class ExemplarIdGenerator implements IdentifierGenerator {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8; // número de caracteres do ID
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder sb = new StringBuilder("EX-");
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return sb.toString(); // Exemplo: "EX-A3Z9K1LQ"
    }
}
