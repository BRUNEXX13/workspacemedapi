package com.example.med.api.repository.exame;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.med.api.model.Exame;
import com.example.med.api.repository.filter.ExameFilter;

public interface ExameRepositoryQuery {
	
	public Page<Exame> filtrar(ExameFilter exameFilter, Pageable pageable);

}
