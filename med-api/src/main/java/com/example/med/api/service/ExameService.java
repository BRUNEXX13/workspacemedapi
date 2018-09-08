package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Exame;
import com.example.med.api.repository.ExameRepository;

//@Service para dizer que e um componente do Spring
@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;

	public Exame atualizar(Long codigo, Exame exame) {
		Exame exameSalvo = exameRepository.findOne(codigo);
		if (exameSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(exame, exameSalvo, "codigo");
		return exameRepository.save(exameSalvo);
	}

}