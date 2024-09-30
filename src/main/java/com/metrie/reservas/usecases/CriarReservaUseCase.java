package com.metrie.reservas.usecases;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;

@Component
public class CriarReservaUseCase {

    public ReservaEntity criarReserva(UsuarioEntity usuario, RestauranteEntity restaurante, LocalDateTime horarioInicio) {

        if (!restaurante.estaAberto(horarioInicio.toLocalTime())) {
            throw new RuntimeException("Restaurante não está aberto nesse horario");
        }

        var reserva = new ReservaEntity(usuario, restaurante, horarioInicio);

        return reserva;
    }
}
