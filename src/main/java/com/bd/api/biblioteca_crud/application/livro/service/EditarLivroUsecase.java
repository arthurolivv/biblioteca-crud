package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.application.exemplar.dto.request.CadastrarExemplarDto;
import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.livro.LivroPertenceCategoria;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditarLivroUsecase {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Transactional
    public Livro editarLivro(EditarLivroDto dto, String isbn) {

        Livro livro = livroRepository.findById(isbn).get();

        Editora editora = editoraRepository.getReferenceById(dto.editora_cnpj());
        livro.setEditora(editora);

        livro.setTitulo(dto.titulo());
        livro.setAno_publicacao(dto.ano_publicacao());
        livro.setSinopse(dto.sinopse());
        livro.setIdioma(dto.idioma());
        livro.setImagem_url(dto.imagem_url());

        atualizarCategorias(livro, dto.categoria_id());
        atualizarAutores(livro, dto.autor_oplid());
        atualizarExemplares(livro, dto.exemplares());

        livro.setQuantidade(calculaQuantidade(livro.getExemplares()));
        livro.setDisponiveis(calculaDisponiveis(livro.getExemplares()));

        return livroRepository.save(livro);
    }

    private void atualizarAutores(Livro livro, List<String> autoresOplid) {
        List<Autor> novosAutores = autorRepository.findAllById(autoresOplid);

        // Remove autores que não estão mais
        livro.getAutorEscreveLivro().removeIf(ael ->
                novosAutores.stream().noneMatch(na -> na.getOplid().equals(ael.getAutor().getOplid()))
        );

        // Adiciona novos autores
        for (Autor autor : novosAutores) {
            boolean existe = livro.getAutorEscreveLivro().stream()
                    .anyMatch(ael -> ael.getAutor().getOplid().equals(autor.getOplid()));
            if (!existe) {
                livro.getAutorEscreveLivro().add(new AutorEscreveLivro(livro, autor));
            }
        }
    }

    private void atualizarCategorias(Livro livro, List<Long> categoriasId) {
        List<Categoria> novasCategorias = categoriaRepository.findAllById(categoriasId);

        livro.getLivroPertenceCategoria().removeIf(lpc ->
                novasCategorias.stream().noneMatch(nc -> nc.getId().equals(lpc.getCategoria().getId()))
        );

        for (Categoria cat : novasCategorias) {
            boolean existe = livro.getLivroPertenceCategoria().stream()
                    .anyMatch(lpc -> lpc.getCategoria().getId().equals(cat.getId()));
            if (!existe) {
                livro.getLivroPertenceCategoria().add(new LivroPertenceCategoria(livro, cat));
            }
        }
    }

    private void atualizarExemplares(Livro livro, List<CadastrarExemplarDto> exemplaresDto) {
        List<Exemplar> existentes = livro.getExemplares();
        List<String> codigosDto = exemplaresDto.stream()
                .map(CadastrarExemplarDto::codigo_exemplar)
                .toList();

        // Remover exemplares que não existem mais
        existentes.removeIf(e -> !codigosDto.contains(e.getId().getCodigo_exemplar()));

        // Atualizar ou adicionar novos
        for (CadastrarExemplarDto dto : exemplaresDto) {
            Exemplar ex = existentes.stream()
                    .filter(e -> e.getId().getCodigo_exemplar().equals(dto.codigo_exemplar()))
                    .findFirst().orElse(null);

            if (ex != null) {
                ex.setProprio(dto.proprio());
            } else {
                existentes.add(new Exemplar(dto, livro));
            }
        }
    }

    private Short calculaQuantidade(List<Exemplar> exemplares){

        var quantidade = exemplares.stream().count();
        return (short) quantidade;
    }

    private Short calculaDisponiveis(List<Exemplar> exemplares){

        var proprios = exemplares.stream()
                .filter(Exemplar::ehProprio)
                .count();

        var disponiveis = calculaQuantidade(exemplares) - proprios;

        return (short) disponiveis;
    }
}
