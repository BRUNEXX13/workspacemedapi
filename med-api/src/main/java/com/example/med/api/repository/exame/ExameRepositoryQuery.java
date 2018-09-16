package com.example.med.api.repository.exame;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.med.api.model.Exame;
import com.example.med.api.repository.filter.ExameFilter;
import com.example.med.api.repository.projection.ResumoExame;

public interface ExameRepositoryQuery {

	public Page<Exame> filtrar(ExameFilter exameFilter, Pageable pageable);
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable);

}
