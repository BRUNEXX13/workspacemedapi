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
import com.example.med.api.model.Medico;
import com.example.med.api.repository.MedicoRepository;
import com.example.med.api.service.MedicoService;

/*
 *  Rest e trabalhado Atraves de Recursos ( PUT < POST < DELETE )
 *  Essa classe vai expor tudo que esta relacionado com as anotações abaixo:/
 */

@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	@Autowired
	private MedicoRepository medicoRespository;

	@Autowired
	private MedicoService medicoService;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	public List<Medico> listar() {
		return medicoRespository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	public ResponseEntity<Medico> criar(@Valid @RequestBody Medico medico, HttpServletResponse response) {
		Medico medicoSalvo = medicoRespository.save(medico);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Medico> buscarPeloCodigo(@PathVariable Long codigo) {
		Medico medico = medicoRespository.findOne(codigo);
		return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		medicoRespository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Medico> atualizar(@PathVariable Long codigo, @Valid @RequestBody Medico medico) {
		Medico medicoSalvo = medicoService.atualizar(codigo, medico);
		return ResponseEntity.ok(medicoSalvo);
	}

}
