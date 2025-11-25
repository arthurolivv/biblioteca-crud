package com.bd.api.biblioteca_crud.application.relatorio.service;

import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioAutorDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioCategoriaDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioEditoraDto;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioLivroDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bd.api.biblioteca_crud.application.relatorio.dto.response.RelatorioStatusEmprestimoDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public List<RelatorioCategoriaDto> getEmprestimosPorCategoria() {
        return relatorioRepository.contarEmprestimosPorCategoria();
    }

    public List<RelatorioEditoraDto> getTop10EditorasMaisEmprestadas() {
        return relatorioRepository.encontrarTop10EditorasMaisEmprestadas();
    }

    public List<RelatorioAutorDto> getTop10AutoresMaisEmprestados() {
        return relatorioRepository.encontrarTop10AutoresMaisEmprestados();
    }

    public List<RelatorioLivroDto> getTop10LivrosMaisEmprestados() {
        return relatorioRepository.encontrarTop10LivrosMaisEmprestados();
    }

    public List<RelatorioStatusEmprestimoDto> getEmprestimosPorStatus() {
        return relatorioRepository.contarEmprestimosPorStatus();
    }
}