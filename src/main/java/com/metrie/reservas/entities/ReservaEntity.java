package com.metrie.reservas.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "RESERVAS")
public class ReservaEntity {

    @Id
    @Column
    @GeneratedValue()
    private UUID id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private UsuarioEntity usuario;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private RestauranteEntity restaurante;

    @Column
    private LocalDateTime horarioInicial;

    public ReservaEntity(UsuarioEntity usuario, RestauranteEntity restaurante, LocalDateTime horarioInicial) {
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.horarioInicial = horarioInicial;
    }
}
