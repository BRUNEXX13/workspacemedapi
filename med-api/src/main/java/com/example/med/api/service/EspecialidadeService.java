package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Especialidade;
import com.example.med.api.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	public Especialidade atualizar(Long codigo, Especialidade especialidade) {
		Especialidade especialidadeSalvo = especialidadeRepository.findOne(codigo);
		if (especialidadeSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(especialidade, especialidadeSalvo, "codigo");
		return especialidadeRepository.save(especialidadeSalvo);
	}

}
