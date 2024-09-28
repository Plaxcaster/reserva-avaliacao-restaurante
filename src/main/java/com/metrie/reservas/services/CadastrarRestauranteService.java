package com.metrie.reservas.services;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;

@Component
public class CadastrarRestauranteService {

    @Autowired
    RestaurantesRepository restaurantesRepository;

    public RestauranteEntity cadastrarRestaurante(String nome, String regiao, String tipo) {
        RestauranteEntity restaurante = new RestauranteEntity(UUID.randomUUID(), nome, regiao,
                TipoDeCozinhaEnum.valueOf(tipo), LocalTime.NOON, LocalTime.MIDNIGHT);

        return restaurantesRepository.save(restaurante);
    }
}
