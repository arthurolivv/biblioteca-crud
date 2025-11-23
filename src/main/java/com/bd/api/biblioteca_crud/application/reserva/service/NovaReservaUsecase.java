package com.bd.api.biblioteca_crud.application.reserva.service;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.reserva.ReservaId;
import com.bd.api.biblioteca_crud.domain.reserva.UsuarioReservaLivro;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.infraestructure.persistence.ReservaRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class NovaReservaUsecase {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;

    public void execute(
            String cpf,
            String isbn_livro,
            String data_reserva
    ){

        ReservaId reservaId = new ReservaId(isbn_livro, cpf, UUID.randomUUID());
        UsuarioReservaLivro reserva = new UsuarioReservaLivro();
        reserva.setId(reservaId);
        reserva.setStatus(StatusReserva.PENDENTE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataRes = LocalDate.parse(data_reserva, formatter);
        reserva.setData_reserva(dataRes);

        Usuario usuario = usuarioRepository.getReferenceById(cpf);
        reserva.setUsuario(usuario);

        Livro livro = livroRepository.getReferenceById(isbn_livro);
        reserva.setLivro(livro);

        reservaRepository.save(reserva);
    }
}
