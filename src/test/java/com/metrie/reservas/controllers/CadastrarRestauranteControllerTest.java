package com.metrie.reservas.controllers;

import java.security.InvalidParameterException;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;

public class CadastrarRestauranteControllerTest {

    @Mock
    private RestaurantesRepository repository;

    AutoCloseable openMock;

    private CadastrarRestauranteController controller;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
        controller = new CadastrarRestauranteController(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void CadastrarRestauranteDeveEsperarHorarioEntradaFormatado() {
        assertThatThrownBy(() -> {
            controller.cadastrarRestaurante("nome", "regiao", "CHINESA", "02AM", "12:10" , "1");
        }).isInstanceOf(InvalidParameterException.class);

        assertThatThrownBy(() -> {
            controller.cadastrarRestaurante("nome", "regiao", "CHINESA", " ", "12:10" , "1");
        }).isInstanceOf(InvalidParameterException.class);

    }

    @Test
    void CadastrarRestauranteDeveEsperarHorarioFechamentoFormatado() {
        assertThatThrownBy(() -> {
            controller.cadastrarRestaurante("nome", "regiao", "CHINESA", "10:00", "10AM" , "1");
        }).isInstanceOf(InvalidParameterException.class);

        assertThatThrownBy(() -> {
            controller.cadastrarRestaurante("nome", "regiao", "CHINESA", "10:00", "1000" , "1");
        }).isInstanceOf(InvalidParameterException.class);
    }

    @Test
    void CadastrarRestauranteDeveSalvarNaTabela() {

        when(repository.save(any())).thenReturn(criaRestaurante());

        var restauranteCadastrado = controller.cadastrarRestaurante("nome","regiao","CHINESA","12:00","00:00", "1" );

        assertThat(restauranteCadastrado).isNotNull();

        assertThat(restauranteCadastrado.getNome()).isEqualTo(criaRestaurante().getNome());
        assertThat(restauranteCadastrado.getHorarioAbertura()).isEqualTo(criaRestaurante().getHorarioAbertura());
        assertThat(restauranteCadastrado.getHorarioFechamento()).isEqualTo(criaRestaurante().getHorarioFechamento());
        assertThat(restauranteCadastrado.getTipoDeCozinha()).isEqualTo(criaRestaurante().getTipoDeCozinha());
                             
        verify(repository , times(1)).save(any());
    }

    private RestauranteEntity criaRestaurante() {
        return new RestauranteEntity("nome", "regiao", LocalTime.NOON, LocalTime.MIDNIGHT, TipoDeCozinhaEnum.CHINESA , 1);
    }
}
