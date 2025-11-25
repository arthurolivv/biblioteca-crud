package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.domain.autor.Autor;
import com.bd.api.biblioteca_crud.application.autor.dto.request.AutorCadastroDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.AutorRepository;
import com.bd.api.biblioteca_crud.domain.shared.enums.Nacionalidade;
import jakarta.validation.Valid;
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

    /**
     * Exibe a página de visualização detalhada de um autor específico.
     */
    @GetMapping("/{oplid}")
    public String showVisualizarAutorPagina(@PathVariable String oplid, Model model) {
        Autor autor = autorRepository.findById(oplid)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        model.addAttribute("autor", autor);
        model.addAttribute("livros", autor.getEscreve());

        return "autor/visualizar";
    }

    /**
     * Exibe a página de listagem de todos os autores.
     */
    @GetMapping({"", "/"})
    public String showListarAutorPagina(Model model) {
        List<Autor> autores = autorRepository.findAll();
        long totalNacionalidades = calcularTotalNacionalidades(autores);

        model.addAttribute("autores", autores);
        model.addAttribute("totalNacionalidades", totalNacionalidades);

        if (!model.containsAttribute("novoAutorDto")) {
            model.addAttribute("novoAutorDto", new AutorCadastroDto(null, null));
        }

        model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());

        return "autor/lista";
    }

    /**
     * Exibe a página dedicada de cadastro de autor.
     */
    @GetMapping("/novo")
    public String showCadastrarAutorPagina(Model model) {
        if (!model.containsAttribute("novoAutorDto")) {
            model.addAttribute("novoAutorDto", new AutorCadastroDto(null, null));
        }

        model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());

        return "autor/cadastrar";
    }

    /**
     * Processa o cadastro de autor pela página dedicada (POST /autores/novo).
     */
    @PostMapping("/novo")
    public String cadastrarAutorPaginaDedicada(@Valid @ModelAttribute("novoAutorDto") AutorCadastroDto dto,
                                               BindingResult result,
                                               RedirectAttributes attributes,
                                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("novoAutorDto", dto);
            model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());
            model.addAttribute("errorMessage", "Erro de validação! Verifique os campos.");
            return "autor/cadastrar";
        }

        try {
            Autor novoAutor = converterDtoParaAutor(dto);
            autorRepository.save(novoAutor);
            attributes.addFlashAttribute("successMessage", "Autor '" + dto.nome() + "' cadastrado com sucesso!");
            return "redirect:/autores";
        } catch (Exception e) {
            model.addAttribute("novoAutorDto", dto);
            model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());
            model.addAttribute("errorMessage", "Erro ao cadastrar: " + e.getMessage());
            return "autor/cadastrar";
        }
    }

    /**
     * Processa o cadastro via Modal (POST /autores) - Mantido para compatibilidade.
     */
    @PostMapping
    public String cadastrarAutorModal(@Valid @ModelAttribute("novoAutorDto") AutorCadastroDto dto,
                                      BindingResult result,
                                      RedirectAttributes attributes) {
        if (result.hasErrors()) {
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
        }

        return "redirect:/autores";
    }

    /**
     * NOVO: Exibe a página de edição de autor.
     */
    @GetMapping("/editar/{oplid}")
    public String showEditarAutorPagina(@PathVariable String oplid, Model model) {
        Autor autor = autorRepository.findById(oplid)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        // Converte a entidade para DTO
        AutorCadastroDto dto = new AutorCadastroDto(autor.getNome(), autor.getNacionalidade());

        model.addAttribute("autorDto", dto);
        model.addAttribute("autorOplid", oplid);
        model.addAttribute("nacionalidadesDisponiveis", Nacionalidade.values());

        return "autor/editar";
    }

    /**
     * NOVO: Processa a edição de um autor (via Modal - usando @RequestParam).
     */
    @PostMapping("/editar/{oplid}")
    public String editarAutor(@PathVariable String oplid,
                              @RequestParam("nome") String nome,
                              @RequestParam("nacionalidade") String nacionalidadeStr,
                              RedirectAttributes attributes) {

        try {
            // Busca o autor existente
            Autor autorExistente = autorRepository.findById(oplid)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

            // Validação manual simples
            if (nome == null || nome.trim().isEmpty()) {
                attributes.addFlashAttribute("errorMessage", "O nome é obrigatório.");
                return "redirect:/autores";
            }

            if (nome.length() > 255) {
                attributes.addFlashAttribute("errorMessage", "O nome não pode exceder 255 caracteres.");
                return "redirect:/autores";
            }

            // Converte a string para o Enum
            Nacionalidade nacionalidade;
            try {
                nacionalidade = Nacionalidade.valueOf(nacionalidadeStr);
            } catch (IllegalArgumentException e) {
                attributes.addFlashAttribute("errorMessage", "Nacionalidade inválida.");
                return "redirect:/autores";
            }

            // Atualiza os dados do autor
            autorExistente.setNome(nome.trim());
            autorExistente.setNacionalidade(nacionalidade);

            autorRepository.save(autorExistente);

            attributes.addFlashAttribute("successMessage", "Autor '" + nome + "' atualizado com sucesso!");

        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
        }

        return "redirect:/autores";
    }

    /**
     * NOVO: Remove um autor (via POST para segurança).
     */
    @PostMapping("/remover/{oplid}")
    public String removerAutor(@PathVariable String oplid, RedirectAttributes attributes) {
        try {
            Autor autor = autorRepository.findById(oplid)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

            String nomeAutor = autor.getNome();

            // Verifica se o autor tem livros associados
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

        return "redirect:/autores";
    }

    /**
     * Gera um OPLID único no formato OL[10 dígitos]A.
     */
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

    /**
     * Converte DTO (record) para entidade Autor com OPLID gerado.
     */
    private Autor converterDtoParaAutor(AutorCadastroDto dto) {
        Autor autor = new Autor();
        autor.setOplid(gerarNovoOplid());
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        return autor;
    }

    /**
     * Calcula o número de nacionalidades únicas.
     */
    private long calcularTotalNacionalidades(List<Autor> autores) {
        Set<Nacionalidade> nacionalidadesUnicas = autores.stream()
                .map(Autor::getNacionalidade)
                .collect(Collectors.toSet());
        return nacionalidadesUnicas.size();
    }
}