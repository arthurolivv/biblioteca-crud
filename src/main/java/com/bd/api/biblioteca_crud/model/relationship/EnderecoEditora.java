package com.bd.api.biblioteca_crud.model.relationship;

import com.bd.api.biblioteca_crud.model.entity.Editora;
import jakarta.persistence.*;

@Entity(name = "Endereco_Editora")
@Table(name = "Endereco_Editora")
public class EnderecoEditora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_editora_razao_social", nullable = false)
    private Editora editora;
}
