package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.response.EditarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class EditarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void show(Model model, String cpf) {

        try {
            Usuario usuario = usuarioRepository.getReferenceById(cpf);
            model.addAttribute("usuario", usuario);

            EditarUsuarioDto editarUsuarioDto = new EditarUsuarioDto(usuario);

            model.addAttribute("editarUsuarioDto", editarUsuarioDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void execute(EditarUsuarioDto dto, String cpf) {

        //editar


    }
}
