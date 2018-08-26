package com.example.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Medico;

//Pegando do Model MedicoRepository 
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
