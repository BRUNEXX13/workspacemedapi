package com.example.med.api.resource;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.med.api.event.RecursoCriadoEvent;
import com.example.med.api.model.Paciente;
import com.example.med.api.repository.PacienteRepository;
import com.example.med.api.service.PacienteService;

/*
 *  Rest e trabalhado Atraves de Recursos ( PUT < POST < DELETE )
 *  Essa classe vai expor tudo que esta relacionado com as anotações abaixo:/
 */


@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		Paciente pacienteSalvo = pacienteRepository.save(paciente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pacienteSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PACIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		Paciente paciente = pacienteRepository.findOne(codigo);
		return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PACIENTE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		pacienteRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = pacienteService.atualizar(codigo, paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}

	// Atualizacao Parcial de Paciente
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACIENTE') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pacienteService.atualizarPropriedadeAtivo(codigo, ativo);

	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PACIENTE')")
	public Page<Paciente> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return pacienteRepository.findByNomeContaining(nome, pageable);
}
	
	


}
