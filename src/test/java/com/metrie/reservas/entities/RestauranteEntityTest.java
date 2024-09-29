package com.metrie.reservas.entities;

import org.junit.jupiter.api.Test;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalTime;

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

    @Test
    void funcaoVerificaAberturaFuncionaParaAberturaAntesDoFechamento() {
        var restaurante = new RestauranteEntity();
        restaurante.setHorarioAbertura(LocalTime.of(10, 0));
        restaurante.setHorarioFechamento(LocalTime.of(14, 0));

        assertThat(restaurante.estaAberto(LocalTime.of(12, 0))).isTrue();
        assertThat(restaurante.estaAberto(LocalTime.of(9, 0))).isFalse();
        assertThat(restaurante.estaAberto(LocalTime.of(15, 0))).isFalse();
    }

    @Test
    void funcaoVerificaAberturaFuncionaParaFechamentoAntesDaAbertura() {
        var restaurante = new RestauranteEntity();
        restaurante.setHorarioAbertura(LocalTime.of(14, 0));
        restaurante.setHorarioFechamento(LocalTime.of(10, 0));

        assertThat(restaurante.estaAberto(LocalTime.of(12, 0))).isFalse();
        assertThat(restaurante.estaAberto(LocalTime.of(9, 59))).isTrue();
        assertThat(restaurante.estaAberto(LocalTime.of(14, 1))).isTrue();
    }
}
