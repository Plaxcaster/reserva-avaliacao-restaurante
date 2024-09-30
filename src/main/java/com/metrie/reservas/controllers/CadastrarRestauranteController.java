package com.metrie.reservas.controllers;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.TipoDeCozinhaEntity;
import com.metrie.reservas.repositories.RestaurantesRepository;

@Component
public class CadastrarRestauranteController {

    RestaurantesRepository restaurantesRepository;
    
    public CadastrarRestauranteController(RestaurantesRepository restaurantesRepository) {
        this.restaurantesRepository = restaurantesRepository;
    }

    public RestauranteEntity cadastrarRestaurante(String nome, String regiao, String tipo, String horarioAbertura,
            String horarioFechamento , String capacidade) {

        DateTimeFormatter parserHorario = DateTimeFormatter.ofPattern("H:mm");
        LocalTime horarioAberturaLocalTime; 
        LocalTime horarioFechamentoLocalTime;
        int capacidadeint;
        
        try {
            horarioAberturaLocalTime =LocalTime.parse(horarioAbertura , parserHorario);
        } catch (Exception e) {
            throw new InvalidParameterException("Horario de abertura deve ser no padrão HH:mm");
        }

        try {
            horarioFechamentoLocalTime = LocalTime.parse(horarioFechamento , parserHorario);
        } catch (Exception e) {
            throw new InvalidParameterException("Horario de fechamento deve ser no padrão HH:mm");
        }

        try {
            capacidadeint = Integer.parseInt(capacidade);
        } catch (Exception e) {
            throw new InvalidParameterException("Capacidade do Restaurante precisa ser um número" );
        }
        RestauranteEntity restaurante = new RestauranteEntity(nome, regiao, horarioAberturaLocalTime, horarioFechamentoLocalTime,
                TipoDeCozinhaEntity.converteTipoDeCozinha(tipo), capacidadeint);

        return restaurantesRepository.save(restaurante);
    }
}
