package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.usuario.dto.response.ListarUsuarios;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import com.bd.api.biblioteca_crud.infraestructure.specification.UsuarioSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarUsuarioUsecase {

    private final UsuarioRepository usuarioRepository;

    public String execute(Model model, String ordem, String busca, String status, String categoriaId) {
        List<Usuario> usuarios;

        // 1. Lógica para ordenação especial (Empréstimos/Reservas)
        if ("emprestimos_desc".equals(ordem)) {
            usuarios = usuarioRepository.findAllOrderByEmprestimosDesc();
        } else if ("emprestimos_asc".equals(ordem)) {
            usuarios = usuarioRepository.findAllOrderByEmprestimosAsc();
        } else if ("reservas_desc".equals(ordem)) {
            usuarios = usuarioRepository.findAllOrderByReservasDesc();
        } else if ("reservas_asc".equals(ordem)) {
            usuarios = usuarioRepository.findAllOrderByReservasAsc();
        } else {

            Specification<Usuario> spec = Specification
                    .where(UsuarioSpecification.comBusca(busca))
                    .and(UsuarioSpecification.comStatus(status))
                    .and(UsuarioSpecification.comCategoria(categoriaId));

            Sort sort = Sort.by("nome.priNome").ascending();

            if ("nome_desc".equals(ordem)) {
                sort = Sort.by("nome.priNome").descending();
            } else if ("cpf_asc".equals(ordem)) {
                sort = Sort.by("cpf").ascending();
            } else if ("cpf_desc".equals(ordem)) {
                sort = Sort.by("cpf").descending();
            } else if ("email_asc".equals(ordem)) {
                sort = Sort.by("email").ascending();
            } else if ("email_desc".equals(ordem)) {
                sort = Sort.by("email").descending();
            } else if ("status_asc".equals(ordem)) {
                sort = Sort.by("deleted").ascending();
            } else if ("status_desc".equals(ordem)) {
                sort = Sort.by("deleted").descending();
            }

            usuarios = usuarioRepository.findAll(spec, sort);
        }

        if (ordem.contains("emprestimos") || ordem.contains("reservas")) {

            // Filtro manual por Busca
            if (busca != null && !busca.trim().isEmpty()) {
                String buscaLower = busca.toLowerCase().trim();
                usuarios = usuarios.stream()
                        .filter(u -> u.getNome().getPriNome().toLowerCase().contains(buscaLower) ||
                                u.getCpf().toLowerCase().contains(buscaLower) ||
                                u.getEmail().toLowerCase().contains(buscaLower))
                        .collect(Collectors.toList());
            }

            // Filtro manual por Status
            if (status != null && !status.trim().isEmpty()) {
                boolean statusBoolean = Boolean.parseBoolean(status);
                usuarios = usuarios.stream()
                        .filter(u -> u.getDeleted() == statusBoolean)
                        .collect(Collectors.toList());
            }
        }

        // Converter para DTO
        List<ListarUsuarios> usuariosDto = usuarios.stream()
                .map(ListarUsuarios::new)
                .collect(Collectors.toList());

        model.addAttribute("usuarios", usuariosDto);

        // Estatísticas
        long ativos = usuariosDto.stream()
                .filter(u -> Boolean.FALSE.equals(u.status()))
                .count();
        model.addAttribute("ativos", ativos);

        long inativos = usuariosDto.stream()
                .filter(u -> Boolean.TRUE.equals(u.status()))
                .count();
        model.addAttribute("inativos", inativos);

        // CORREÇÃO: Adicionar lista vazia de categorias (se você não tiver categorias de usuário)
        model.addAttribute("categorias", List.of());

        // Adicionar atributos de filtros
        model.addAttribute("ordemSelecionada", ordem != null ? ordem : "nome_asc");
        model.addAttribute("buscaSelecionada", busca != null ? busca : "");
        model.addAttribute("statusSelecionado", status != null ? status : "");
        model.addAttribute("categoriaSelecionada", categoriaId != null ? categoriaId : "");

        return "usuarios/lista";
    }
}