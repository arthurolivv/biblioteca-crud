package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.application.usuario.dto.response.EditarUsuarioDto;
import com.bd.api.biblioteca_crud.application.usuario.service.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final DesativarUsuarioUsecase desativar;
    private final AtivarUsuarioUsecase ativar;
    private final ListarUsuarioUsecase listar;
    private final CadastrarUsuarioUsecase cadastrar;
    private final EditarUsuarioUsecase editar;
    private final ExcluirUsuarioUsecase excluir;
    private final VisualizarUsuarioUsecase visualizar;
    private final CadastrarUsuarioValidationService validar;

    @GetMapping({"", "/"})
    public String showListarUsuarioPagina(Model model) {
        return listar.execute(model);
    }

    @GetMapping("/cadastro")
    public String showCadastrarUsuarioPagina(Model model) {

        cadastrar.show(model);
        return "usuarios/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(
            @Valid @ModelAttribute CadastrarUsuarioDto dto,
            BindingResult result
    ) {

        validar.execute(dto, result);

        if (result.hasErrors()) {
            return "usuarios/cadastro";
        }

        cadastrar.execute(dto);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar")
    public String showEditarUsuarioPagina(Model model, @RequestParam String cpf) {

        editar.show(model, cpf);
        return "usuarios/editar";
    }

    @PostMapping("/editar")
    public String editarUsuario(
            Model model,
            @RequestParam String cpf,
            @Valid @ModelAttribute EditarUsuarioDto dto,
            @RequestParam(required = false) String senhaAtual
    ) {
        try {
            editar.execute(dto, cpf, senhaAtual, model);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            model.addAttribute("error", "Erro ao editar usuário");
            return "usuarios/editar";
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/visualizar")
    public String showVisualizarUsuarioPagina(Model model, @RequestParam String cpf) {

        try {
            visualizar.show(model, cpf);
        } catch (Exception e) {
            return "redirect:/usuarios";
        }
        return "usuarios/visualizar";
    }

    @GetMapping("/excluir")
    public String excluirUsuario(@RequestParam String cpf) {

        excluir.execute(cpf);
        return "redirect:/usuarios";
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
