package com.example.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Especialidade;


// Pegando do Model Especialidade Repository 
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	
		
}
