package com.bd.api.biblioteca_crud.application.exemplar.service;

import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarExemplaresDisponiveisUsecase {

    @Autowired
    private ExemplarRepository exemplarRepository;

}
