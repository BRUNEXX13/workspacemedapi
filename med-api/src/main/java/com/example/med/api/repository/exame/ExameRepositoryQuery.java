package com.example.med.api.repository.exame;

import java.util.List;

import com.example.med.api.model.Exame;
import com.example.med.api.repository.filter.ExameFilter;

public interface ExameRepositoryQuery {
	
	public List<Exame> filtrar(ExameFilter exameFilter);

}
