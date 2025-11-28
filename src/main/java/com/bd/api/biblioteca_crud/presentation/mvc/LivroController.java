package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.dto.response.EditarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.service.*;
import com.bd.api.biblioteca_crud.infraestructure.specification.LivroSpecification;
import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.editora.Editora;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroUsecase cadastrar;
    private final EditarLivroUsecase editar;
    private final DesativarLivroUsecase desativar;
    private final AtivarLivroUsecase ativar;
    private final VisualizarLivroUsecase visualizar;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private CadastrarLivroValidationService validar;

    @GetMapping({"", "/"})
    public String showListarLivroPagina(
            Model model,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String autorId,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String idioma,
            @RequestParam(required = false) Optional<Boolean> status,
            @RequestParam(defaultValue = "titulo_asc") String ordem) {

        // 1. Construir a Especificação dinâmica (cláusula WHERE)
        Specification<Livro> spec = Specification.where(LivroSpecification.comBusca(busca))
                .and(LivroSpecification.comAutor(autorId))
                .and(LivroSpecification.comCategoria(categoriaId))
                .and(LivroSpecification.comIdioma(idioma))
                .and(LivroSpecification.comStatus(status.orElse(null)));

        // 2. Criar o objeto de Ordenação (cláusula ORDER BY)
        Sort sort = criarOrdenacao(ordem);

        // 3. Executar a query OTIMIZADA (filtra e ordena no DB)
        List<Livro> livros = livroRepository.findAll(spec, sort);

        // 4. Carregar dados de suporte para os filtros
        List<Categoria> categorias = categoriaRepository.findAll();
        List<Autor> autores = autorRepository.findAll();

        // 5. Adicionar a nova métrica: Total de Exemplares
        Long totalExemplares = livroRepository.countTotalExemplares();

        model.addAttribute("livros", livros);
        model.addAttribute("categorias", categorias);
        model.addAttribute("autores", autores);
        model.addAttribute("idiomas", Idioma.values());
        model.addAttribute("totalExemplares", totalExemplares != null ? totalExemplares : 0); // Novo atributo

        // 6. Manter valores dos filtros selecionados (para persistência no formulário Thymeleaf)
        model.addAttribute("buscaSelecionada", busca != null ? busca : "");
        model.addAttribute("autorSelecionado", autorId != null ? autorId : "");
        model.addAttribute("categoriaSelecionada", categoriaId != null ? categoriaId : "");
        model.addAttribute("idiomaSelecionado", idioma != null ? idioma : "");
        model.addAttribute("statusSelecionado", status.isPresent() ? status.get().toString() : "");
        model.addAttribute("ordemSelecionada", ordem);

        return "livros/lista";
    }

    /**
     * Converte o parâmetro de String 'ordem' para um objeto Sort do Spring Data.
     */
    private Sort criarOrdenacao(String ordem) {
        return switch (ordem) {
            case "titulo_desc" -> Sort.by("titulo").descending();
            case "ano_asc" -> Sort.by("ano_publicacao").ascending();
            case "ano_desc" -> Sort.by("ano_publicacao").descending();
            case "disponiveis_desc" -> Sort.by("disponiveis").descending();
            case "disponiveis_asc" -> Sort.by("disponiveis").ascending();
            case "titulo_asc" -> Sort.by("titulo").ascending();
            default -> Sort.by("titulo").ascending();
        };
    }

// -----------------------------------------------------------------------------------------
// Métodos de CRUD (Manutenção)
// -----------------------------------------------------------------------------------------

    @GetMapping("/cadastro")
    public String showCadastrarLivroPagina(Model model) {

        cadastrar.show(model);
        return "livros/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarLivro(
            @Valid @ModelAttribute CadastrarLivroDto dto,
            BindingResult result,
            Model model) {

        validar.execute(dto, result);

        if (result.hasErrors()) {
            return "livros/cadastro";
        }

        cadastrar.execute(dto);

        return "redirect:/livros";
    }

    @GetMapping("/editar")
    public String showEditarLivroPagina(Model model, @RequestParam String isbn) {

        try {

            editar.show(model, isbn);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/livros";
        }

        return "livros/editar";
    }

    @PostMapping("/editar")
    public String editarLivro(
            Model model,
            @RequestParam String isbn,
            @Valid @ModelAttribute EditarLivroDto dto,
            BindingResult result
    ) {
        try {
            editar.execute(dto, isbn);

        } catch (Exception e) {
            System.out.println("Exception ao editar: " + e.getMessage());
            editar.show(model, isbn);
            return "livros/editar";
        }
        return "redirect:/livros";
    }

    @GetMapping("/visualizar")
    public String showVisualizarLivroPagina(Model model, @RequestParam String isbn) {

        try {
            visualizar.execute(model, isbn);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/livros";
        }

        return "livros/visualizar";
    }

    @GetMapping("/desativar")
    public String desativarLivro(@RequestParam String isbn) {

        try {
            desativar.execute(isbn);
        } catch (Exception e) {
            System.out.println("Exception ao desativar: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    @GetMapping("/ativar")
    public String ativarLivro(@RequestParam String isbn) {

        try {
            ativar.execute(isbn);
        } catch (Exception e) {
            System.out.println("Exception ao ativar: " + e.getMessage());
        }
        return "redirect:/livros";
    }

}