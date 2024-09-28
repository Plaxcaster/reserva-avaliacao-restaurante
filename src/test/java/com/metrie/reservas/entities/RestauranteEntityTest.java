package com.metrie.reservas.entities;

import org.junit.jupiter.api.Test;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

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

    @Test
    void nomeDoRestauranteNaoPodeSerNulo() {
        RestauranteEntity resturante = new RestauranteEntity();
        assertThatThrownBy(() -> {
            resturante.setNome(null);
        }).isInstanceOf(Exception.class);
    }

    @Test
    void nomeDoRestauranteNaoPodeSerVazio() {
        RestauranteEntity resturante = new RestauranteEntity();
        assertThatThrownBy(() -> {
            resturante.setNome("");
        }).isInstanceOf(Exception.class);
    }

    @Test
    void regiaoDoRestauranteNaoPodeSerNulo() {
        RestauranteEntity resturante = new RestauranteEntity();
        assertThatThrownBy(() -> {
            resturante.setRegiao(null);
        }).isInstanceOf(Exception.class);
    }

    @Test
    void regiaoDoRestauranteNaoPodeSerVazio() {
        RestauranteEntity resturante = new RestauranteEntity();
        assertThatThrownBy(() -> {
            resturante.setRegiao("");
        }).isInstanceOf(Exception.class);
    }
}
