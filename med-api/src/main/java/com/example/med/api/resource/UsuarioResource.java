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
import com.example.med.api.model.Usuario;
import com.example.med.api.repository.UsuarioRepository;

//Pegando de Repository 

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Publicador de Eventos de Aplications , = RecursoCriadoEvent
	@Autowired
	private ApplicationEventPublisher publisher;

	// Listando os valores do JSON
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();

	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping // @Valid Ativa o bean Validation
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		// Classe Recurso Criado Event
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);

	}

	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepository.findOne(codigo);

		// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();

	}

	// Remover Especialdiade
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204 - Codigo No Content
	public void remover(@PathVariable Long codigo) {
		usuarioRepository.delete(codigo);

	}

}
