package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.application.exemplar.dto.request.CadastrarExemplarDto;
import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.livro.LivroPertenceCategoria;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarLivroUsecase {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Transactional
    public Livro execute(CadastrarLivroDto dto) {

        var editora = editoraRepository.getReferenceById(dto.editora_cnpj());
        var biblioteca = bibliotecaRepository.getReferenceById("63.025.530/0062‑26");

        Livro novoLivro = new Livro(dto);

        novoLivro.setEditora(editora);
        novoLivro.setBiblioteca(biblioteca);

        novoLivro.setLivroPertenceCategoria(mapearCategorias(novoLivro, dto.categoria_id()));
        novoLivro.setAutorEscreveLivro(mapearAutores(novoLivro, dto.autor_oplid()));
        novoLivro.setExemplares(criarExemplar(novoLivro, dto.exemplares()));

        novoLivro.setQuantidade(calculaQuantidade(novoLivro.getExemplares()));
        novoLivro.setDisponiveis(calculaDisponiveis(novoLivro.getExemplares()));

        return livroRepository.save(novoLivro);
    }

    private List<LivroPertenceCategoria> mapearCategorias(Livro livro, List<Long> categoriasId) {

        List<Categoria> categorias = categoriaRepository.findAllById(categoriasId);
        return categorias.stream()
                .map(cat -> new LivroPertenceCategoria(livro, cat))
                .toList();
    }

    private List<AutorEscreveLivro> mapearAutores(Livro livro, List<String> autoresOplid) {

        List<Autor> autores = autorRepository.findAllById(autoresOplid);
        return autores.stream()
                .map(aut -> new AutorEscreveLivro(livro, aut))
                .toList();
    }

    private List<Exemplar> criarExemplar(Livro livro, List<CadastrarExemplarDto> exemplarDto) {

        return exemplarDto.stream()
                .map(exe -> new Exemplar(exe, livro))
                .toList();
    }

    private Short calculaQuantidade(List<Exemplar> exemplares) {

        var quantidade = exemplares.stream().count();
        return (short) quantidade;
    }

    private Short calculaDisponiveis(List<Exemplar> exemplares) {

        long disponiveisNaoProprios = exemplares.stream()
                .filter(e -> e.getStatus() == StatusExemplar.DISPONIVEL)
                .filter(e -> Boolean.FALSE.equals(e.getProprio()))
                .count();

        return (short) disponiveisNaoProprios;
    }
}