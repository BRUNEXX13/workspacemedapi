package com.example.med.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.med.api.event.RecursoCriadoEvent;
import com.example.med.api.model.Especialidade;
import com.example.med.api.repository.EspecialidadeRepository;
import com.example.med.api.service.EspecialidadeService;

//Pegando de Repository 

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private EspecialidadeService especialidadeService;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIALIDADE') and #oauth2.hasScope('read')")
	public List<Especialidade> listar() {
		return especialidadeRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESPECIALIDADE') and #oauth2.hasScope('write')")
	public ResponseEntity<Especialidade> criar(@Valid @RequestBody Especialidade especialidade, HttpServletResponse response) {
		Especialidade especialidadeSalvo = especialidadeRepository.save(especialidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, especialidadeSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeSalvo);
	}



	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIALIDADE') and #oauth2.hasScope('read')")
	public ResponseEntity<Especialidade> buscarPeloCodigo(@PathVariable Long codigo) {
	Especialidade especialidade = especialidadeRepository.findOne(codigo);
		return especialidade != null ? ResponseEntity.ok(especialidade) : ResponseEntity.notFound().build();
}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		especialidadeRepository.delete(codigo);
}

	@PutMapping("/{codigo}")
	public ResponseEntity<Especialidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Especialidade especialidade) {
		Especialidade especialidadeSalvo = especialidadeService.atualizar(codigo, especialidade);
		return ResponseEntity.ok(especialidadeSalvo);
	}

}
