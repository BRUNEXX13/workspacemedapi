package com.example.med.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.med.api.model.Especialidade;
import com.example.med.api.repository.EspecialidadeRepository;

//Pegando de Repository 

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	// Listando os valores do JSON
	@GetMapping
	public List<Especialidade> listar() {
		return especialidadeRepository.findAll();

	}

	// @ CRIAR Uma Especialidade - Status Code 201 Created //
	// Criando Valors através do JSON
	@PostMapping
	public ResponseEntity<Especialidade> criar(@RequestBody Especialidade especialidade, HttpServletResponse response) {
		Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);

		// Devolvendo o valor criado para o jason
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/[codigo]")
				.buildAndExpand(especialidadeSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		// Apos ser Criado uma especialidade, recebe o codigo criado //
		// @ResponseStatus(HttpStatus.CREATED), Retornando os valores da API;
		return ResponseEntity.created(uri).body(especialidadeSalva);

	}

	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Especialidade> buscarPeloCodigo(@PathVariable Long codigo) {
		Especialidade especialidade = especialidadeRepository.findOne(codigo);
		
		// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return especialidade != null ? ResponseEntity.ok(especialidade) : ResponseEntity.notFound().build();

	}

	// Se meu Json trouxer valores Nulos // 204 Not Content
	// @GetMapping
	// public ResponseEntity<?> listar() {
	// List<Especialidade> especialidades = especialidadeRepository.findAll();
	// return !especialidades.isEmpty() ? ResponseEntity.ok(especialidades) :
	// ResponseEntity.noContent().build();
	// }

}
