package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.response.EditarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.Endereco;
import com.bd.api.biblioteca_crud.domain.shared.bases.Nome;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class EditarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String show(Model model, String cpf) {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(cpf);
            model.addAttribute("usuario", usuario);

            EditarUsuarioDto editarUsuarioDto = new EditarUsuarioDto(usuario);

            model.addAttribute("editarUsuarioDto", editarUsuarioDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/usuarios";
        }
        return null;
    }

    @Transactional
    public void execute(EditarUsuarioDto dto, String cpf, String senhaAtual, Model model) {

        try {
            // Buscar o usuário gerenciado pelo JPA
            Usuario usuario = usuarioRepository.findById(cpf)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            // Verificar senha atual se está tentando alterar
            if (dto.senha() != null && !dto.senha().trim().isEmpty()) {

                // Verificar se informou a senha atual
                if (senhaAtual == null || senhaAtual.trim().isEmpty()) {
                    model.addAttribute("error", "Para alterar a senha, informe a senha atual!");
                    model.addAttribute("usuario", usuario);
                }

                // Verificar se a senha atual está correta
                if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
                    model.addAttribute("senhaErrada", true);
                    model.addAttribute("error", "Senha atual incorreta!");
                    model.addAttribute("usuario", usuario);
                }

                String senhaCriptografada = passwordEncoder.encode(dto.senha());
                usuario.setSenha(senhaCriptografada);
            }

            // Criar novo objeto Nome
            Nome novoNome = new Nome(
                    dto.nome().pri_nome(),
                    dto.nome().sob_nome()
            );

            // Criar novo objeto Endereço
            Endereco novoEndereco = new Endereco(
                    dto.endereco().cep(),
                    dto.endereco().cidade(),
                    dto.endereco().estado(),
                    dto.endereco().rua(),
                    dto.endereco().numero(),
                    dto.endereco().complemento(),
                    dto.endereco().bairro(),
                    dto.endereco().pais()
            );

            // Atualizar os campos do usuário
            usuario.setRg(dto.rg());
            usuario.setEmail(dto.email());
            usuario.setNome(novoNome);
            usuario.setEndereco(novoEndereco);

            // Salvar
            usuarioRepository.save(usuario);

        } catch (Exception e) {
            System.out.println("Erro ao editar usuário: " + e.getMessage());
            model.addAttribute("error", "Erro ao editar usuário: " + e.getMessage());
        }
    }
}