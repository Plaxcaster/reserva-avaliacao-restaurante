package com.metrie.reservas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrie.reservas.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    public UsuarioEntity findByNome(String nome);
}