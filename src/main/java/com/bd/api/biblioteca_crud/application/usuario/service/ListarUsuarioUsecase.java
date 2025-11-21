package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.response.ListarUsuarios;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ListarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String execute(Model model) {
        List<ListarUsuarios> usuarios = usuarioRepository.findAll().stream()
                .map(ListarUsuarios::new)
                .toList();

        model.addAttribute("usuarios", usuarios);

        return "usuarios/lista";
    }
}
