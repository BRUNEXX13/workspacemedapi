package com.example.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Exame;


// Pegando do Model Especialidade Repository 
public interface ExameRepository extends JpaRepository<Exame, Long> {
	
		
}
