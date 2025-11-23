package com.bd.api.biblioteca_crud.application.reserva;

import com.bd.api.biblioteca_crud.domain.reserva.ReservaId;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import com.bd.api.biblioteca_crud.infraestructure.persistence.ReservaRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class CancelarReservaUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    public void execute(
             String cpf,
             String isbn_livro,
             UUID codigo_reserva
    ){
        ReservaId reservaId = new ReservaId(isbn_livro, cpf, codigo_reserva);
        UsuarioReservaLivro reserva = reservaRepository.getReferenceById(reservaId);
        reserva.setStatus(StatusReserva.CANCELADA);

        reservaRepository.save(reserva);
    }
}
