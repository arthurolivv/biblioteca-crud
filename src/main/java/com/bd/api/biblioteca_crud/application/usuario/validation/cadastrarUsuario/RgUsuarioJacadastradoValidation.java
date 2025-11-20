package com.bd.api.biblioteca_crud.application.usuario.validation.cadastrarUsuario;

import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.shared.Validation;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RgUsuarioJacadastradoValidation implements Validation<CadastrarUsuarioDto> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validate(CadastrarUsuarioDto dto, Errors errors) {

        if (usuarioRepository.existsById(dto.rg())) {
            errors.rejectValue("rg", null, "RG já cadastrado no sistema");
        }
    }
}
