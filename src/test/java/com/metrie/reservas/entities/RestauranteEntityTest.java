package com.metrie.reservas.entities;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.TipoDeCozinhaEnum;

import static org.assertj.core.api.Assertions.*;

public class RestauranteEntityTest {

    @Test
    void deveSerEstanciado() {
        RestauranteEntity restaurante = new RestauranteEntity();

        assert (restaurante.getClass() == RestauranteEntity.class);
    }

    @Test
    void deveTerGetterESetter() {
        RestauranteEntity restaurante = new RestauranteEntity();

        restaurante.setNome("nome");
        restaurante.setRegiao("regiao");
        restaurante.setTipoDeCozinha(TipoDeCozinhaEnum.CHINESA);
        assertThat(restaurante.getNome()).isEqualTo("nome");
        assertThat(restaurante.getRegiao()).isEqualTo("regiao");
        assertThat(restaurante.getTipoDeCozinha()).isEqualTo(TipoDeCozinhaEnum.CHINESA);
    }
}
