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
import com.example.med.api.model.Exame;
import com.example.med.api.repository.ExameRepository;

//Pegando de Repository 

@RestController
@RequestMapping("/exames")
public class ExameResource {

	@Autowired
	private ExameRepository exameRepository;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	public List<Exame> listar() {
		return exameRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping // @Valid Ativa o bean Validation
	public ResponseEntity<Exame> criar(@Valid @RequestBody Exame exame, HttpServletResponse response) {
		Exame exameSalvo = exameRepository.save(exame);

		// Classe Recurso Criado Event
		publisher.publishEvent(new RecursoCriadoEvent(this, response, exameSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(exameSalvo);

	}

	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Exame> buscarPeloCodigo(@PathVariable Long codigo) {
		Exame exame = exameRepository.findOne(codigo);

		// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return exame != null ? ResponseEntity.ok(exame) : ResponseEntity.notFound().build();

	}

	// Remover Especialdiade
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204 - Codigo No Content
	public void remover(@PathVariable Long codigo) {
		exameRepository.delete(codigo);

	}

}
