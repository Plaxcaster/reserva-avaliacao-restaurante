package com.metrie.reservas.entities;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.util.UUID;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RESTAURANTES")
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
        if (nome.isEmpty()) {
            throw new InvalidParameterException("nome não pode ser vazio");
        }
        this.nome = nome;
    }
    
    public void setTipoDeCozinha(TipoDeCozinhaEnum tipoDeCozinha){
        this.tipoDeCozinha = tipoDeCozinha;
    }
    
    public void setRegiao(String regiao){
        if (regiao.isEmpty()) {
            throw new InvalidParameterException("regiao não pode ser vazio");
        }
        this.regiao = regiao;
    }
}
