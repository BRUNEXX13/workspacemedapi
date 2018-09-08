package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Medico;
import com.example.med.api.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	
	public Medico atualizar(Long codigo, Medico medico) {
		Medico medicoSalvo= medicoRepository.findOne(codigo);
		if (medicoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(medico, medicoSalvo, "codigo");
		return medicoRepository.save(medicoSalvo);
	}

}
