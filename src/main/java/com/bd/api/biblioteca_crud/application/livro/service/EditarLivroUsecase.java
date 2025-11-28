package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.application.exemplar.dto.request.CadastrarExemplarDto;
import com.bd.api.biblioteca_crud.application.exemplar.dto.request.EditarExemplarDto;
import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.autor.AutorEscreveLivro;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.livro.LivroPertenceCategoria;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void show(Model model, String isbn){

        Livro livro = livroRepository.getReferenceById(isbn);
        model.addAttribute("livro", livro);

        EditarLivroDto editarLivroDto = new EditarLivroDto(livro);

        model.addAttribute("editarLivroDto", editarLivroDto);

        List<Autor> autores = autorRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();
        List<Editora> editoras = editoraRepository.findAll();

        model.addAttribute("idiomas", Idioma.values());
        model.addAttribute("autores", autores);
        model.addAttribute("categorias", categorias);
        model.addAttribute("editoras", editoras);
    }

    @Transactional
    public Livro execute(EditarLivroDto dto, String isbn) {

        Livro livro = livroRepository.getReferenceById(isbn);

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

    private void atualizarExemplares(Livro livro, List<EditarExemplarDto> exemplaresDto) {

        List<Exemplar> existentes = livro.getExemplares();

        // Lista de códigos enviados pelo formulário
        List<String> codigosDto = exemplaresDto.stream()
                .map(EditarExemplarDto::codigo_exemplar)
                .toList();

        // REMOVER exemplares não enviados
        existentes.removeIf(ex ->
                !codigosDto.contains(ex.getId().getCodigo_exemplar())
        );

        // atualizar existentes ou add novos
        for (EditarExemplarDto dto : exemplaresDto) {

            Exemplar ex = existentes.stream()
                    .filter(e -> e.getId().getCodigo_exemplar().equals(dto.codigo_exemplar()))
                    .findFirst()
                    .orElse(null);

            if (ex != null) {

                // Atualiza somente se tiver mudado
                if (!ex.getProprio().equals(dto.proprio())) {
                    ex.setProprio(dto.proprio());
                }

                if (!ex.getStatus().equals(dto.status())) {
                    ex.setStatus(dto.status());
                }

            } else {
                // Criar novo exemplar
                existentes.add(new Exemplar(dto, livro));
            }
        }
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
