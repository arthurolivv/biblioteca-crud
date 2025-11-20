package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.service.DesativarLivroUsecase;
import com.bd.api.biblioteca_crud.application.usuario.dto.response.ListarUsuarios;
import com.bd.api.biblioteca_crud.application.usuario.service.AtivarUsuarioUsecase;
import com.bd.api.biblioteca_crud.application.usuario.service.DesativarUsuarioUsecase;
import com.bd.api.biblioteca_crud.application.usuario.service.ListarUsuarioUsecase;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final DesativarUsuarioUsecase desativar;
    private final AtivarUsuarioUsecase ativar;
    private final ListarUsuarioUsecase listar;

    @GetMapping({"", "/"})
    public String showListarUsuarioPagina(Model model) {

        listar.execute(model);
        return "usuarios/lista";
    }

    @GetMapping("/desativar")
    public String desativarUsuario(@RequestParam String cpf) {

        desativar.execute(cpf);
        return "redirect:/usuarios";
    }

    @GetMapping("/ativar")
    public String ativarUsuario(@RequestParam String cpf) {

        ativar.execute(cpf);
        return "redirect:/usuarios";
    }
}
