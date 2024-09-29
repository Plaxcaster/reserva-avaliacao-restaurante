package com.metrie.reservas.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.repositories.UsuarioRepository;

public class CadastrarUsuarioControllerTest {

    @Mock
    private UsuarioRepository repository;

    AutoCloseable openMock;

    private CadastrarUsuarioController controller;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
        controller = new CadastrarUsuarioController(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void testCadastrarUsuario() {
        UsuarioEntity usuario = new UsuarioEntity("Bob");
        when(repository.save(any())).thenReturn(usuario);

        assertThat(controller.cadastrarUsuario("Bob")).isNotNull().isEqualTo(usuario);

    }
}
