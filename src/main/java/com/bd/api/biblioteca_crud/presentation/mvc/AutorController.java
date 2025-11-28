package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.application.autor.dto.request.AutorCadastroDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.AutorRepository;
import com.bd.api.biblioteca_crud.domain.shared.enums.Nacionalidade;
import com.bd.api.biblioteca_crud.infraestructure.specification.AutorSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;
    private final Random random = new Random();

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // -------------------------------------------------------------------------
    // ROTA PRINCIPAL: LISTAGEM COM FILTROS E ORDENAÇÃO (GARANTE O MODEL)
    // -------------------------------------------------------------------------

    @GetMapping({"", "/"})
    public String showListarAutorPagina(
            Model model,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String nacionalidade,
            @RequestParam(defaultValue = "nome_asc") String ordem) {

        // Toda a lógica de busca/filtro/ordenação é mantida aqui.
        List<Autor> autores;
        Nacionalidade nacionalidadeEnum = null;

        if (nacionalidade != null && !nacionalidade.trim().isEmpty()) {
            try {
                nacionalidadeEnum = Nacionalidade.valueOf(nacionalidade);
            } catch (IllegalArgumentException e) {}
        }

        if ("livros_desc".equals(ordem)) {
            autores = autorRepository.findAllOrderByLivroCountDescFiltered(nacionalidadeEnum);
        } else if ("livros_asc".equals(ordem)) {
            autores = autorRepository.findAllOrderByLivroCountAscFiltered(nacionalidadeEnum);
        } else {
            Specification<Autor> spec = Specification.where(AutorSpecification.comNome(busca))
                    .and(AutorSpecification.comNacionalidade(nacionalidade));

            Sort sort = Sort.by("nome").ascending();
            if ("nome_desc".equals(ordem)) {
                sort = Sort.by("nome").descending();
            }

            autores = autorRepository.findAll(spec, sort);
        }

        // Garante que todas as variáveis essenciais estão no Model
        long totalNacionalidades = calcularTotalNacionalidades(autorRepository.findAll());

        model.addAttribute("autores", autores);
        model.addAttribute("totalNacionalidades", totalNacionalidades);

        // ESSENCIAL: Garante que o DTO de cadastro (para o modal) está no Model.
        // Se houver erro de validação (POST), ele virá via FlashAttribute.
        if (!model.containsAttribute("novoAutorDto")) {
            model.addAttribute("novoAutorDto", new AutorCadastroDto(null, null));
        }

        model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());

        // Manter valores dos filtros
        model.addAttribute("buscaSelecionada", busca != null ? busca : "");
        model.addAttribute("nacionalidadeSelecionada", nacionalidade != null ? nacionalidade : "");
        model.addAttribute("ordemSelecionada", ordem);

        return "autor/lista";
    }

    // -------------------------------------------------------------------------
    // CADASTRO (CORRIGIDO PARA O PADRÃO REDIRECT)
    // -------------------------------------------------------------------------

    @PostMapping
    public String ModalCadastroAutor(@Valid @ModelAttribute("novoAutorDto") AutorCadastroDto dto,
                                     BindingResult result,
                                     RedirectAttributes attributes) {

        if (result.hasErrors()) {
            // Se houver erro de validação, usa Flash Attributes e REDIRECIONA
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.novoAutorDto", result);
            attributes.addFlashAttribute("novoAutorDto", dto);
            attributes.addFlashAttribute("errorMessage", "Erro de validação! Verifique os campos.");
            return "redirect:/autores";
        }

        try {
            Autor novoAutor = converterDtoParaAutor(dto);
            autorRepository.save(novoAutor);
            attributes.addFlashAttribute("successMessage", "Autor '" + dto.nome() + "' cadastrado com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro ao cadastrar: " + e.getMessage());
            // Mantém o DTO no FlashAttribute para reabrir o modal com dados
            attributes.addFlashAttribute("novoAutorDto", dto);
        }

        // SEMPRE redireciona para o GET /autores
        return "redirect:/autores";
    }

    // -------------------------------------------------------------------------
    // EDIÇÃO (CORRIGIDO PARA O PADRÃO REDIRECT)
    // -------------------------------------------------------------------------

    @PostMapping("/editar/{oplid}")
    public String editarAutor(@PathVariable String oplid,
                              @RequestParam("nome") String nome,
                              @RequestParam("nacionalidade") String nacionalidadeStr,
                              RedirectAttributes attributes) {
        try {
            Autor autorExistente = autorRepository.findById(oplid)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

            // Validação manual (em caso de falha, usa FlashAttribute e Redirect)
            if (nome == null || nome.trim().isEmpty() || nome.length() > 255) {
                attributes.addFlashAttribute("errorMessage", "O nome é obrigatório e deve ter até 255 caracteres.");
                return "redirect:/autores";
            }

            Nacionalidade nacionalidade;
            try {
                nacionalidade = Nacionalidade.valueOf(nacionalidadeStr);
            } catch (IllegalArgumentException e) {
                attributes.addFlashAttribute("errorMessage", "Nacionalidade inválida.");
                return "redirect:/autores";
            }

            autorExistente.setNome(nome.trim());
            autorExistente.setNacionalidade(nacionalidade);
            autorRepository.save(autorExistente);

            attributes.addFlashAttribute("successMessage", "Autor '" + nome + "' atualizado com sucesso!");

        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
        }

        // SEMPRE redireciona para o GET /autores
        return "redirect:/autores";
    }

    // -------------------------------------------------------------------------
    // REMOÇÃO (CORRIGIDO PARA O PADRÃO REDIRECT)
    // -------------------------------------------------------------------------

    @PostMapping("/remover/{oplid}")
    public String removerAutor(@PathVariable String oplid, RedirectAttributes attributes) {
        try {
            Autor autor = autorRepository.findById(oplid)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

            String nomeAutor = autor.getNome();

            if (autor.getEscreve() != null && !autor.getEscreve().isEmpty()) {
                attributes.addFlashAttribute("errorMessage",
                        "Não é possível remover o autor '" + nomeAutor + "' pois ele possui " +
                                autor.getEscreve().size() + " livro(s) associado(s).");
                return "redirect:/autores";
            }

            autorRepository.delete(autor);
            attributes.addFlashAttribute("successMessage",
                    "Autor '" + nomeAutor + "' removido com sucesso!");

        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage",
                    "Erro ao remover autor: " + e.getMessage());
        }

        // SEMPRE redireciona para o GET /autores
        return "redirect:/autores";
    }

    // -------------------------------------------------------------------------
    // VISUALIZAÇÃO E MÉTODOS AUXILIARES (MANTIDOS)
    // -------------------------------------------------------------------------

    @GetMapping("/{oplid}")
    public String showVisualizarAutorPagina(@PathVariable String oplid, Model model) {
        Autor autor = autorRepository.findById(oplid)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        model.addAttribute("autor", autor);
        model.addAttribute("livros", autor.getEscreve());

        return "autor/visualizar";
    }

    private String gerarNovoOplid() {
        String oplid;
        do {
            long min = 1000000000L;
            long max = 9999999999L;
            long number = min + (long) (random.nextDouble() * (max - min));
            oplid = String.format("OL%dA", number);
        } while (autorRepository.existsById(oplid));

        return oplid;
    }

    private Autor converterDtoParaAutor(AutorCadastroDto dto) {
        Autor autor = new Autor();
        autor.setOplid(gerarNovoOplid());
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        return autor;
    }

    private long calcularTotalNacionalidades(List<Autor> autores) {
        Set<Nacionalidade> nacionalidadesUnicas = autores.stream()
                .map(Autor::getNacionalidade)
                .collect(Collectors.toSet());
        return nacionalidadesUnicas.size();
    }
}