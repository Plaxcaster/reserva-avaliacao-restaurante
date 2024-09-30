package com.metrie.reservas.controllers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;

public class ConsultarRestaurantesControllerTest {

    @Mock
    private RestaurantesRepository repository;

    AutoCloseable openMock;

    private ConsultarRestaurantesController controller;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
        controller = new ConsultarRestaurantesController(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void listaRestaurantePorRegiaoDeveRetornarOsResultados() {

        var restaurante1 = new RestauranteEntity();
        var restaurante2 = new RestauranteEntity();
        var restaurante3 = new RestauranteEntity();
        restaurante1.setNome("primeiro");
        restaurante2.setNome("segundo");
        restaurante3.setNome("terceiro");
        List<RestauranteEntity> lista = Arrays.asList(restaurante1, restaurante2, restaurante3);
        when(repository.findByRegiao("Sobradinho")).thenReturn(lista);

        assertThat(controller.listaRestaurantePorRegiao("Sobradinho")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).hasSize(3);

        verify(repository, times(1)).findByRegiao(any());
    }
    @Test
    void listaRestaurantePorNomeDeveRetornarOsResultados() {

        var restaurante1 = new RestauranteEntity();
        var restaurante2 = new RestauranteEntity();
        var restaurante3 = new RestauranteEntity();
        restaurante1.setNome("primeiro");
        restaurante2.setNome("segundo");
        restaurante3.setNome("terceiro");
        List<RestauranteEntity> lista = Arrays.asList(restaurante1, restaurante2, restaurante3);
        when(repository.findByNome("Sobradinho")).thenReturn(lista);

        assertThat(controller.listaRestaurantePorNome("Sobradinho")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).hasSize(3);

        verify(repository, times(1)).findByNome(any());
    }
    @Test
    void listaRestaurantePorTipoDeveRetornarOsResultados() {

        var restaurante1 = new RestauranteEntity();
        var restaurante2 = new RestauranteEntity();
        var restaurante3 = new RestauranteEntity();
        restaurante1.setNome("primeiro");
        restaurante2.setNome("segundo");
        restaurante3.setNome("terceiro");
        List<RestauranteEntity> lista = Arrays.asList(restaurante1, restaurante2, restaurante3);
        when(repository.findByTipoDeCozinha(TipoDeCozinhaEnum.MINEIRA)).thenReturn(lista);

        assertThat(controller.listaRestaurantePorTipoDeCozinha("mineira")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).hasSize(3);

        verify(repository, times(1)).findByTipoDeCozinha(any());
    }

    @Test
    void listaRestaurantePorRegiaoDeveRetornarListaVazioCasoNaoHajaRestaurantesNaConsulta() {

        List<RestauranteEntity> lista = Arrays.asList();

        assertThat(controller.listaRestaurantePorRegiao("Sobradinho")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).isEmpty();
        assertThat(controller.listaRestaurantePorNome("Macdonalds")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).isEmpty();
        assertThat(controller.listaRestaurantePorTipoDeCozinha("chinesa")).isNotNull().isInstanceOf(List.class)
                .isEqualTo(lista).isEmpty();
    }
}
