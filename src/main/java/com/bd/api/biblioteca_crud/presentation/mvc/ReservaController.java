package com.bd.api.biblioteca_crud.presentation.mvc;

import com.bd.api.biblioteca_crud.application.reserva.CancelarReservaUsecase;
import com.bd.api.biblioteca_crud.application.reserva.service.NovaReservaUsecase;
import com.bd.api.biblioteca_crud.application.reserva.service.VerificarReservaUsecase;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final NovaReservaUsecase reserva;
    private final CancelarReservaUsecase cancelar;
    private final VerificarReservaUsecase verificar;

    @PostMapping("/salvar")
    public String salvarReserva(
            @RequestParam String cpf,
            @RequestParam String isbn_livro,
            @RequestParam String data_reserva
    ){

        reserva.execute(cpf, isbn_livro, data_reserva);
        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }

    @PostMapping("/cancelar")
    public String cancelarReserva(
            @RequestParam String cpf,
            @RequestParam String isbn_livro,
            @RequestParam UUID codigo_reserva
    ){

        cancelar.execute(cpf, isbn_livro,codigo_reserva);
        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }

    @PostMapping("/verificar")
    public String verificarSituacaoReserva(
            @RequestParam String cpf,
            @RequestParam String isbn_livro,
            @RequestParam UUID codigo_reserva,
            RedirectAttributes redirectAttributes
    ){

        verificar.execute(cpf, isbn_livro, codigo_reserva, redirectAttributes);
        return "redirect:/usuarios/visualizar?cpf=" + cpf;
    }
}
