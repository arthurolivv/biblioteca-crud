package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.service.DesativarLivroUsecase;
import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.application.usuario.dto.response.EditarUsuarioDto;
import com.bd.api.biblioteca_crud.application.usuario.dto.response.ListarUsuarios;
import com.bd.api.biblioteca_crud.application.usuario.service.*;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarEnderecoDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarNomeDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final DesativarUsuarioUsecase desativar;
    private final AtivarUsuarioUsecase ativar;
    private final ListarUsuarioUsecase listar;
    private final CadastrarUsuarioUsecase cadastrar;
    private final EditarUsuarioUsecase editar;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastrarUsuarioValidationService validar;

    @GetMapping({"", "/"})
    public String showListarUsuarioPagina(Model model) {

        listar.execute(model);
        return "usuarios/lista";
    }

    @GetMapping("/cadastro")
    public String showCadastrarUsuarioPagina(Model model) {

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
        return "usuarios/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(
            @Valid @ModelAttribute CadastrarUsuarioDto dto,
            BindingResult result,
            Model model
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


        try {
            Usuario usuario = usuarioRepository.getReferenceById(cpf);
            model.addAttribute("usuario", usuario);

            EditarUsuarioDto editarUsuarioDto = new EditarUsuarioDto(usuario);

            model.addAttribute("editarUsuarioDto", editarUsuarioDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/usuarios";
        }
        
        return "usuarios/editar";
    }

    @PostMapping("/editar")
    public String editarUsuario(
            Model model,
            @RequestParam String cpf,
            @Valid @ModelAttribute EditarUsuarioDto dto,
            BindingResult result
    ) {

        try {

            if (result.hasErrors()) {
                return "usuarios/editar";
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

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
