package com.bd.api.biblioteca_crud.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean verificarSenha(String senha) {
        return passwordEncoder.matches(senha, criptografarSenha(senha));
    }
}
