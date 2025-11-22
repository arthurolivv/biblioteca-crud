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

        long totalUsuarios = usuarios.size();
        long ativos = usuarios.stream()
                .filter(u -> Boolean.FALSE.equals(u.status())) // status == false => ativo (pelo seu mapeamento)
                .count();
        model.addAttribute("ativos", ativos);

        long inativos = usuarios.stream()
                .filter(u -> Boolean.TRUE.equals(u.status())) // status == true => inativo
                .count();
        model.addAttribute("inativos", inativos);

        return "usuarios/lista";
    }
}
