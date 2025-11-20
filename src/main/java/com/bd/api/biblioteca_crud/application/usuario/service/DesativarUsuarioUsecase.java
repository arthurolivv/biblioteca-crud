package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesativarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void execute(String cpf) {

        try {
            Usuario usuario = usuarioRepository.getReferenceById(cpf);
            usuario.setDeleted(true);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}
