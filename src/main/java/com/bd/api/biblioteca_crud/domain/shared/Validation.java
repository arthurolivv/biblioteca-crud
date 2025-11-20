package com.bd.api.biblioteca_crud.domain.shared;

import org.springframework.validation.Errors;

public interface Validation<T> {

    void validate(T dto, Errors errors);
}
