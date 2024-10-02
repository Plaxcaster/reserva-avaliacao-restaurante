package com.metrie.reservas.controllers;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.enums.TipoDeCozinhaEnum;
import com.metrie.reservas.repositories.RestaurantesRepository;
import com.metrie.reservas.repositories.UsuarioRepository;
import com.metrie.reservas.usecases.CriarReservaUseCase;

import jakarta.persistence.EntityNotFoundException;

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
        String tempo = "1970-01-01 02:00";
        var horario = LocalDateTime.of(LocalDate.EPOCH, LocalTime.of(2, 0));

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
                TipoDeCozinhaEnum.CHINESA , 1);
        var reservaEsperada = new ReservaEntity(usuario, restaurante, LocalDateTime.now());

        when(usuarioRepository.getReferenceById(any())).thenReturn(usuario);
        when(restaurantesRepository.getReferenceById(any())).thenReturn(restaurante);
        when(useCase.criarReserva(any(), any(), any())).thenReturn(reservaEsperada);

        assertThat(controller.criarReserva(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "2024-01-01 02:00"))
                .isNotNull().isEqualTo(reservaEsperada);
    }

    @Test
    void testConverterIdUsuario_InvalidUUID() {
        String invalidUUID = "invalid-uuid";
        assertThatThrownBy(() -> controller.converterIdUsuario(invalidUUID))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Id do usuário em formato invalido");
    }

    @Test
    void testConverterIdRestaurante_InvalidUUID() {
        String invalidUUID = "invalid-uuid";
        assertThatThrownBy(() -> controller.converterIdRestaurante(invalidUUID))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Id do usuário em formato invalido");
    }

    @Test
    void testConverterHorario_InvalidFormat() {
        String invalidDate = "2024-10-01 25:61";
        assertThatThrownBy(() -> controller.converterHorario(invalidDate))
            .isInstanceOf(InvalidParameterException.class)
            .hasMessageContaining("Horario de inicio deve ser no padrão yyyy-MM-dd HH:mm");
    }

    @Test
    void testRecuperarUsuario_NotFound() {
        UUID userId = UUID.randomUUID();
        when(usuarioRepository.getReferenceById(any(UUID.class))).thenThrow(new EntityNotFoundException());
        assertThatThrownBy(() -> controller.recuperarUsuario(userId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Usuario não encontrado");
    }

    @Test
    void testRecuperarRestaurante_NotFound() {
        UUID restauranteId = UUID.randomUUID();
        when(restaurantesRepository.getReferenceById(any(UUID.class))).thenThrow(new EntityNotFoundException());
        assertThatThrownBy(() -> controller.recuperarRestaurante(restauranteId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Restaurante não encontrado");
    }
}
