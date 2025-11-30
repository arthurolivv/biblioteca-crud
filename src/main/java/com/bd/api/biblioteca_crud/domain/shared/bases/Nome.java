package com.bd.api.biblioteca_crud.domain.shared.bases;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nome {

    private String priNome ;

    private String sobNome;

    public String toLowerCase(){
        return "priNome.toLowerCase() " + "sobNome.toLowerCase()";
    }
}
