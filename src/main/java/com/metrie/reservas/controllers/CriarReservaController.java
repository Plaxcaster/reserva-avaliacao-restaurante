package com.metrie.reservas.controllers;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.repositories.RestaurantesRepository;
import com.metrie.reservas.repositories.UsuarioRepository;
import com.metrie.reservas.usecases.CriarReservaUseCase;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CriarReservaController {

    CriarReservaUseCase criarReservaUseCase;
    UsuarioRepository usuarioRepository;
    RestaurantesRepository restaurantesRepository;

    public CriarReservaController( CriarReservaUseCase criarReservaUseCase
                                 , UsuarioRepository usuarioRepository
                                 , RestaurantesRepository restaurantesRepository) {
        this.criarReservaUseCase = criarReservaUseCase;
        this.usuarioRepository = usuarioRepository;
        this.restaurantesRepository = restaurantesRepository;
    }

    public ReservaEntity criarReserva(String idRestaurante, String idUsuario, String stringHorarioInicio) {
        UUID UUIDUsuario = converterIdUsuario(idUsuario);
        UsuarioEntity usuario = recuperarUsuario(UUIDUsuario);

        UUID UUIDRestaurante = converterIdRestaurante(idRestaurante);
        RestauranteEntity restaurante = recuperarRestaurante(UUIDRestaurante);

        LocalTime horarioInicio = converterHorario(stringHorarioInicio);

        return criarReservaUseCase.criarReserva(usuario, restaurante, horarioInicio);
    }

    public UUID converterIdUsuario(String usuario) {
        try {
            return UUID.fromString(usuario);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Id do usuário em formato invalido", e);
        }
    }

    public UUID converterIdRestaurante(String restaurante) {
        try {
            return UUID.fromString(restaurante);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Id do usuário em formato invalido", e);
        }
    }

    public LocalTime converterHorario(String horario) {
        DateTimeFormatter parserHorario = DateTimeFormatter.ofPattern("H:mm");

        try {
            return LocalTime.parse(horario, parserHorario);
        } catch (Exception e) {
            throw new InvalidParameterException("Horario de inicio deve ser no padrão HH:mm");
        }
    }

    public UsuarioEntity recuperarUsuario(UUID id) {
        try {
            return usuarioRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Usuario não encontrado", e);
        }
    }

    public RestauranteEntity recuperarRestaurante(UUID id) {
        try {
            return restaurantesRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Restaurante não encontrado", e);
        }
    }
}
