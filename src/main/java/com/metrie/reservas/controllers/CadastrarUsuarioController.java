package com.metrie.reservas.controllers;

import org.springframework.stereotype.Component;

import com.metrie.reservas.entities.UsuarioEntity;
import com.metrie.reservas.repositories.UsuarioRepository;

@Component
public class CadastrarUsuarioController {

    private UsuarioRepository usuarioRepository;
    public CadastrarUsuarioController (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity cadastrarUsuario(String nome){
        return usuarioRepository.save(new UsuarioEntity(nome));
    }

}
