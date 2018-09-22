package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Exame;
import com.example.med.api.model.Paciente;
import com.example.med.api.repository.ExameRepository;
import com.example.med.api.repository.PacienteRepository;
import com.example.med.api.service.exception.PacienteInexistenteOuInativaException;

//@Service para dizer que e um componente do Spring
@Service
public class ExameService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ExameRepository exameRepository;

	public Exame salvar(Exame exame) {
		validarPaciente(exame);

		return exameRepository.save(exame);
	}

	public Exame atualizar(Long codigo, Exame exame) {
		Exame exameSalvo = buscarExameExistente(codigo);
		if (!exame.getPaciente().equals(exameSalvo.getPaciente())) {
			validarPaciente(exame);
		}
		
		//Copia as propridades para o objeto BeanUtils
		BeanUtils.copyProperties(exame, exameSalvo, "codigo");

		return exameRepository.save(exameSalvo);
	}

	private void validarPaciente(Exame exame) {
		Paciente paciente = null;
		if (exame.getPaciente().getCodigo() != 0L) {
			paciente = pacienteRepository.findOne(exame.getPaciente().getCodigo());
		}

		if (paciente == null || paciente.isInativo()) {
			throw new PacienteInexistenteOuInativaException();
		}
	}

	private Exame buscarExameExistente(Long codigo) {
		Exame exameSalvo = exameRepository.findOne(codigo);
		if (exameSalvo == null) {
			throw new IllegalArgumentException();
		}
		return exameSalvo;
	}

}
/*
 * // Salvando apenas uma pessoa Ativa public Exame salvar(Exame exame) {
 * Paciente paciente =
 * pacienteRepository.findOne(exame.getPaciente().getCodigo()); if (paciente ==
 * null || paciente.isInativo()) { throw new
 * PacienteInexistenteOuInativaException(); }
 * 
 * return exameRepository.save(exame); }
 * 
 * /* public Exame atualizar(Long codigo, Exame exame) { Exame exameSalvo =
 * exameRepository.findOne(codigo); if (exameSalvo == null) { throw new
 * EmptyResultDataAccessException(1); }
 * 
 * BeanUtils.copyProperties(exame, exameSalvo, "codigo"); return
 * exameRepository.save(exameSalvo); }
 */
