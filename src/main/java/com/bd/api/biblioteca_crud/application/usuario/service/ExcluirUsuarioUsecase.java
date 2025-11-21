package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void execute(String cpf) {
        usuarioRepository.deleteById(cpf);
    }
}
