package com.metrie.reservas.usecases;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;

@Component
public class CriarReservaUseCase {

    public ReservaEntity criarReserva(UsuarioEntity usuario, RestauranteEntity restaurante, LocalTime horarioInicio) {

        if (!restaurante.estaAberto(horarioInicio)) {
            throw new RuntimeException("Restaurante não está aberto nesse momento");
        }

        var reserva = new ReservaEntity(usuario, restaurante, horarioInicio);

        return reserva;
    }
}
