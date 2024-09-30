package com.metrie.reservas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;


public interface RestaurantesRepository extends JpaRepository<RestauranteEntity, UUID> {
    public List<RestauranteEntity> findByRegiao(String regiao);
    public List<RestauranteEntity> findByTipoDeCozinha(TipoDeCozinhaEnum tipoDeCozinha);
    public List<RestauranteEntity> findByNome(String nome);
}
