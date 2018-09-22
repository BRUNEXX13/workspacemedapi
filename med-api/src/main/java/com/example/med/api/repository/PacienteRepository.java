package com.example.med.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	public Page<Paciente> findByNomeContaining(String nome, Pageable pageable);
}
