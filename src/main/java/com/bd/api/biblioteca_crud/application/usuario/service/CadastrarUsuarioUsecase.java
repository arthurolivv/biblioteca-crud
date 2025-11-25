package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarEnderecoDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarNomeDto;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CadastrarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

        public void show(Model model){

            // Criar os objetos aninhados (também devem ser records)
            CadastrarNomeDto nome = new CadastrarNomeDto("", "");

            CadastrarEnderecoDto endereco = new CadastrarEnderecoDto(

                    "", "", "", "", "", "", "", ""
            );

            // Criar o DTO principal na ordem CORRETA do record
            CadastrarUsuarioDto cadastrarUsuarioDto = new CadastrarUsuarioDto(

                    "",         // cpf
                    "",         // rg
                    nome,       // nome
                    "",         // email
                    null,       // data_nasc
                    "",         // senha
                    endereco    // endereco
            );

            model.addAttribute("cadastrarUsuarioDto", cadastrarUsuarioDto);
        }

    public Usuario execute(CadastrarUsuarioDto dto) {

        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario novoUsuario = new Usuario(dto, senhaCriptografada);
        return usuarioRepository.save(novoUsuario);
    }
}
