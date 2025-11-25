package com.bd.api.biblioteca_crud.application.reserva;

import com.bd.api.biblioteca_crud.domain.reserva.ReservaId;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
