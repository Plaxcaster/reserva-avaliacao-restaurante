package com.metrie.reservas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;

@Component
public class CadastrarRestauranteController {

    RestaurantesRepository restaurantesRepository;
    
    @Autowired
    public CadastrarRestauranteController(RestaurantesRepository restaurantesRepository) {
        this.restaurantesRepository = restaurantesRepository;
    }

    public RestauranteEntity cadastrarRestaurante(String nome, String regiao, String tipo, String horarioAbertura,
            String horarioFechamento) {

        DateTimeFormatter parserHorario = DateTimeFormatter.ofPattern("H:mm");
        LocalTime horarioAberturaLocalTime; 
        LocalTime horarioFechamentoLocalTime;
        
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

        RestauranteEntity restaurante = new RestauranteEntity(nome, regiao, horarioAberturaLocalTime, horarioFechamentoLocalTime,
                TipoDeCozinhaEnum.valueOf(tipo));

        return restaurantesRepository.save(restaurante);
    }

}
