package com.metrie.reservas.entities;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioEntityTest {

    private UsuarioEntity usuario;
    private final String NOME_PADRAO = "João";

    @BeforeEach
    void setup() {
        usuario = new UsuarioEntity(NOME_PADRAO);
    }

    @Test
    void testGetId() {
        UUID id = UUID.randomUUID();
        usuario.setId(id);

        assertThat(usuario.getId())
                .isNotNull()
                .isEqualTo(id);
    }

    @Test
    void testGetNome() {
        assertThat(usuario.getNome()).isNotNull().isEqualTo(NOME_PADRAO);
    }
}
