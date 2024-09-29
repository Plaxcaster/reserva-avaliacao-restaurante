package com.metrie.reservas.controllers;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;
import com.metrie.reservas.repositories.UsuarioRepository;
import com.metrie.reservas.usecases.CriarReservaUseCase;

public class CriarReservaControllerTest {

    CriarReservaController controller;

    @Mock
    private CriarReservaUseCase useCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestaurantesRepository restaurantesRepository;

    AutoCloseable openMock;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
        controller = new CriarReservaController(useCase, usuarioRepository, restaurantesRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void testConverterHorarioDeveConverterStringParaLocalTime() {
        String tempo = "02:00";
        var horario = LocalTime.of(02, 0);

        assertThat(controller.converterHorario(tempo)).isNotNull().isEqualTo(horario);
    }

    @Test
    void testConverterHorarioDeveEmitirErroSeFormatoInvalido() {
        assertThatThrownBy(() -> {
            controller.converterHorario("0200");
        }).isInstanceOf(InvalidParameterException.class);
    }

    @Test
    void testConverterIdDevemConverterIgual() {
        UUID id = UUID.randomUUID();
        UUID idRestaurante = controller.converterIdRestaurante(id.toString());
        UUID idUsuario = controller.converterIdUsuario(id.toString());

        assertThat(idRestaurante).isNotNull().isEqualTo(id).isEqualTo(idUsuario);
    }

    @Test
    void testCriarReserva() {
        var usuario = new UsuarioEntity("Bob");
        var restaurante = new RestauranteEntity("restaurante", "Sobradinho", LocalTime.NOON, LocalTime.MIDNIGHT,
                TipoDeCozinhaEnum.CHINESA);
        var reservaEsperada = new ReservaEntity(usuario, restaurante, LocalTime.now());

        when(usuarioRepository.getReferenceById(any())).thenReturn(usuario);
        when(restaurantesRepository.getReferenceById(any())).thenReturn(restaurante);
        when(useCase.criarReserva(any(), any(), any())).thenReturn(reservaEsperada);

        assertThat(controller.criarReserva(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "02:00"))
                .isNotNull().isEqualTo(reservaEsperada);
    }
}
