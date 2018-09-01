package com.example.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
