package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisualizarUsuarioUsecase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void show(Model model, String cpf){
        Usuario usuario = usuarioRepository.getReferenceById(cpf);
        model.addAttribute("usuario", usuario);


        List<Exemplar> exemplaresDisponiveis = exemplarRepository.findAll().stream()
                .filter(ex -> !ex.getLivro().isDeleted())
                .filter(ex -> ex.getStatus() == StatusExemplar.DISPONIVEL)
                .toList();
        model.addAttribute("exemplaresDisponiveis", exemplaresDisponiveis);

        List<Livro> livrosSemExemplaresDisponíveis = livroRepository.findAll().stream()
                .filter(livro -> {
                    return livro.getExemplares().stream()
                            .noneMatch(exemplar -> exemplar.getStatus() == StatusExemplar.DISPONIVEL);
                })
                .collect(Collectors.toList());

        model.addAttribute("livros", livrosSemExemplaresDisponíveis);
    }
}
