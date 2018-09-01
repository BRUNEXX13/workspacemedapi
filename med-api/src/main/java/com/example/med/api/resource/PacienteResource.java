package com.example.med.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	PacienteRepository pacienteRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando By Json
	@GetMapping
	public List<Paciente> listarPaciente() {
		return pacienteRepository.findAll();
	}

	// @ CRIAR Uma Especialidade - Status Code 201 Created //
	// Criando Valors através do JSON @Valid Bean Validator

	@PostMapping // @Valid Ativa o bean Validation
	public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		Paciente pacienteSalvo = pacienteRepository.save(paciente);

		// Classe Recurso Criado Event
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);

	}

	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		Paciente paciente = pacienteRepository.findOne(codigo);

		// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();

	}

	// Remover Um Paciente
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204 - Codigo No Content
	public void remover(@PathVariable Long codigo) {
		pacienteRepository.delete(codigo);
	}

	// Atualizar Pessoa
	@PutMapping("/{codigo]")
	public ResponseEntity<Paciente> Atualizar (@PathVariable Long codigo, @Valid @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = pacienteRepository.findOne(codigo);
		
		// Pega os dados de Paciente, passa para Pacientesalvo ,tirando o codigo para atualizar
		BeanUtils.copyProperties(paciente, pacienteSalvo, "codigo");
		pacienteRepository.save(pacienteSalvo);
		return ResponseEntity.ok(pacienteSalvo);
	}
}
