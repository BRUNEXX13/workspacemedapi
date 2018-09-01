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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.med.api.event.RecursoCriadoEvent;
import com.example.med.api.model.Especialidade;
import com.example.med.api.repository.EspecialidadeRepository;
import com.example.med.api.repository.PacienteRepository;

//Pegando de Repository 

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	public List<Especialidade> listar() {
		return especialidadeRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping // @Valid Ativa o bean Validation
	public ResponseEntity<Especialidade> criar(@Valid @RequestBody Especialidade especialidade,
			HttpServletResponse response) {
		Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);

		// Classe Recurso Criado Event
		publisher.publishEvent(new RecursoCriadoEvent(this, response, especialidadeSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeSalva);

	}

	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Especialidade> buscarPeloCodigo(@PathVariable Long codigo) {
		Especialidade especialidade = especialidadeRepository.findOne(codigo);

		// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return especialidade != null ? ResponseEntity.ok(especialidade) : ResponseEntity.notFound().build();

	}

	// Remover Especialdiade
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204 - Codigo No Content
	public void remover(@PathVariable Long codigo) {
		especialidadeRepository.delete(codigo);

	}

}
