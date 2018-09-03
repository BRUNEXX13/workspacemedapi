package com.example.med.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.med.api.event.RecursoCriadoEvent;
import com.example.med.api.model.Paciente;
import com.example.med.api.repository.PacienteRepository;
import com.example.med.api.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteRepository PacienteRepository;
	
	@Autowired
	private PacienteService pacienteService;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	public List<Paciente> listar() {
		return PacienteRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		Paciente pacienteSalvo = PacienteRepository.save(paciente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}



	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		Paciente paciente = PacienteRepository.findOne(codigo);
		return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		PacienteRepository.delete(codigo);
}

	@PutMapping("/{codigo}")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = pacienteService.atualizar(codigo, paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}

}
