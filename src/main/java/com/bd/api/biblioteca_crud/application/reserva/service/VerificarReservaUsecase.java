package com.bd.api.biblioteca_crud.application.reserva.service;

import com.bd.api.biblioteca_crud.application.emprestimo.service.NovoEmprestimoUsecase;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.reserva.ReservaId;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ReservaRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificarReservaUsecase {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ExemplarRepository exemplarRepository;
    private final NovoEmprestimoUsecase novoEmprestimo;

    public void execute(
             String cpf,
             String isbn_livro,
             UUID codigo_reserva,
            RedirectAttributes redirectAttributes
    ){

        ReservaId reservaId = new ReservaId(isbn_livro, cpf, codigo_reserva);
        UsuarioReservaLivro reserva = reservaRepository.getReferenceById(reservaId);

        Optional<Exemplar> exemplarDisponivel = exemplarRepository.findAll().stream()
                .filter(e -> e.getId().getLivro_isbn().equals(isbn_livro))
                .filter(e -> e.getStatus().equals(StatusExemplar.DISPONIVEL))
                .findFirst();

        if(exemplarDisponivel.isPresent()){
            Exemplar exemplar = exemplarDisponivel.get();
            Usuario usuario = usuarioRepository.getReferenceById(cpf);

            novoEmprestimo.execute(cpf,
                    exemplar.getId().getLivro_isbn(),
                    exemplar.getId().getCodigo_exemplar(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(7));

            reserva.setStatus(StatusReserva.CONCLUIDA);
            reservaRepository.save(reserva);

        }
        else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Nenhum exemplar disponível no momento. Tente novamente mais tarde.");
        }

    }
}
