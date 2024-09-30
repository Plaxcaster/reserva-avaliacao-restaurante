package com.metrie.reservas.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.TipoDeCozinhaEntity;
import com.metrie.reservas.repositories.RestaurantesRepository;

@Component
public class ConsultarRestaurantesController {

    private RestaurantesRepository repository;

    public ConsultarRestaurantesController(RestaurantesRepository repository) {
        this.repository = repository;
    }

    public List<RestauranteEntity> listaRestaurantePorNome(String nomeString) {
        return repository.findByNome(nomeString);
    }

    public List<RestauranteEntity> listaRestaurantePorRegiao(String regiaoString) {
        return repository.findByRegiao(regiaoString);
    }

    public List<RestauranteEntity> listaRestaurantePorTipoDeCozinha(String tipoDeCozinhaString) {
        var tipoDeCozinha = TipoDeCozinhaEntity.converteTipoDeCozinha(tipoDeCozinhaString);
        return repository.findByTipoDeCozinha(tipoDeCozinha);
    }
}
