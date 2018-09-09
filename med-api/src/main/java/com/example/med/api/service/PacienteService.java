package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Paciente;
import com.example.med.api.repository.PacienteRepository;

//@Service para dizer que e um componente do Spring
@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	public Paciente atualizar(Long codigo, Paciente paciente) {
		Paciente pacienteSalvo = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(paciente, pacienteSalvo, "codigo");
		return pacienteRepository.save(pacienteSalvo);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Paciente pacienteSalvo = buscarPessoaPeloCodigo(codigo);
		pacienteSalvo.setAtivo(ativo);
		pacienteRepository.save(pacienteSalvo);

	}

	public Paciente buscarPessoaPeloCodigo(Long codigo) {
		Paciente pacienteSalvo = pacienteRepository.findOne(codigo);
		if (pacienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pacienteSalvo;
	}

}