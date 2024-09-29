package com.metrie.reservas.entities;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.util.UUID;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "RESTAURANTES")
public class RestauranteEntity {

    @Id
    @Column
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    @NotEmpty(message = "nome não pode ser vazio")
    @NonNull
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

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setTipoDeCozinha(TipoDeCozinhaEnum tipoDeCozinha) {
        this.tipoDeCozinha = tipoDeCozinha;
    }

    public void setRegiao(String regiao) {
        validarRegiao(regiao);
        this.regiao = regiao;
    }

    public RestauranteEntity(String nome, String regiao, LocalTime horarioAbertura,
            LocalTime horarioFechamento, TipoDeCozinhaEnum tipoDeCozinhaEnum) {
        validarNome(nome);
        validarRegiao(regiao);

        this.nome = nome;
        this.regiao = regiao;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.tipoDeCozinha = tipoDeCozinhaEnum;
    }

    private void validarNome(String nome) {
        if (nome.isBlank() || nome == null) {
            throw new InvalidParameterException("nome não pode ser vazio");
        }
    }

    private void validarRegiao(String regiao) {
        if (regiao.isBlank() || regiao == null) {
            throw new InvalidParameterException("regiao não pode ser vazio");
        }
    }
}
