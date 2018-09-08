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
import com.example.med.api.model.Exame;
import com.example.med.api.repository.ExameRepository;
import com.example.med.api.service.ExameService;

@RestController
@RequestMapping("/exames")
public class ExameResource {

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private ExameService exameService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Exame> listar() {
		return exameRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	public ResponseEntity<Exame> criar(@Valid @RequestBody Exame exame, HttpServletResponse response) {
		Exame exameSalvo = exameRepository.save(exame);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, exameSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(exameSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Exame> buscarPeloCodigo(@PathVariable Long codigo) {
		Exame exame = exameRepository.findOne(codigo);
		return exame != null ? ResponseEntity.ok(exame) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		exameRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Exame> atualizar(@PathVariable Long codigo, @Valid @RequestBody Exame exame) {
		Exame exameSalvo = exameService.atualizar(codigo, exame);
		return ResponseEntity.ok(exameSalvo);
	}

}
