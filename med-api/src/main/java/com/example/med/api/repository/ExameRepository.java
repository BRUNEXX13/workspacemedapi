package com.example.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.med.api.model.Exame;
import com.example.med.api.repository.exame.ExameRepositoryQuery;

public interface ExameRepository  extends JpaRepository<Exame, Long>, ExameRepositoryQuery  {

}
