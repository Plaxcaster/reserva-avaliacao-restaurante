package com.metrie.reservas.entities;

import java.time.LocalTime;
import java.util.UUID;

import com.TipoDeCozinhaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteEntity {

    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    @NotEmpty(message = "nome não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = "Escolha uma região, por favor")
    private String regiao;

    @Column(nullable = false)
    private TipoDeCozinhaEnum tipoDeCozinha;

    @Column(nullable = false)
    private LocalTime horarioAbertura;

    @Column(nullable = false)
    private LocalTime horarioFechamento;

}
