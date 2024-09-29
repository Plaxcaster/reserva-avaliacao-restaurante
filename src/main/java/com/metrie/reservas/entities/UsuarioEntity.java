package com.metrie.reservas.entities;

import java.security.InvalidParameterException;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {

    @Id
    @Column
    @GeneratedValue()
    private UUID id;

    @Column
    private String nome;

    public UsuarioEntity(String nome) {
        validaNome(nome);

        this.nome = nome;
    }

    private void validaNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new InvalidParameterException("Nome inválido. Não pode ser vazio");
        }
    }
}
