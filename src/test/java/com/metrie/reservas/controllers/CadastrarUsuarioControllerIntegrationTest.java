package com.metrie.reservas.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.repositories.UsuarioRepository;

@SpringBootTest
@Transactional
public class CadastrarUsuarioControllerIntegrationTest {

    @Autowired
    private CadastrarUsuarioController cadastrarUsuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCadastrarUsuario() {
        String nome = "Test User";
        UsuarioEntity usuario = cadastrarUsuarioController.cadastrarUsuario(nome);

        assertThat(usuario).isNotNull();
        assertThat(usuario.getNome()).isEqualTo(nome);

        UsuarioEntity found = usuarioRepository.findByNome(nome);
        assertThat(found).isNotNull();
        assertThat(found.getNome()).isEqualTo(nome);
    }

    @Test
    public void testRetornaUsuarioPorNome() {
        String nome = "Test User";
        UsuarioEntity usuario = new UsuarioEntity(nome);
        usuarioRepository.save(usuario);

        UsuarioEntity found = cadastrarUsuarioController.retornaUsuarioPorNome(nome);
        assertThat(found).isNotNull();
        assertThat(found.getNome()).isEqualTo(nome);
    }
}
