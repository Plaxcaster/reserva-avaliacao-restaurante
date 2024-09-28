package com.metrie.reservas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrie.reservas.entities.RestauranteEntity;

public interface RestaurantesRepository extends JpaRepository<RestauranteEntity, UUID> {

}
