package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario execute(CadastrarUsuarioDto dto) {

        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario novoUsuario = new Usuario(dto, senhaCriptografada);
        return usuarioRepository.save(novoUsuario);
    }
}
