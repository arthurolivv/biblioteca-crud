package com.bd.api.biblioteca_crud.model.relationship;

import com.bd.api.biblioteca_crud.model.entity.Editora;
import jakarta.persistence.*;

@Entity(name = "Endereco_Editora")
@Table(name = "Endereco_Editora")
public class EnderecoEditora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_editora_cnpj", nullable = false)
    private Editora editora;
}
