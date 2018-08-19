package com.example.med.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	// @ CRIAR Uma Especialidade - Status Code 201 Created
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar (@RequestBody Especialidade especialidade, HttpServletResponse response) {
		Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);
		//Devolvendo o valor criado para o jason 
		ServletUriComponentsBuilder.fromCurrentRequestUri().path("/[codigo]")
		.buildAndExpand(especialidadeSalva.getCodigo()).toUri();
		// aula 3.6 response.setDateHeader("Location", uriToAsc);
	}
	

	
	// Se meu Json trouxer valores Nulos // 204 Not Content 
	//@GetMapping
	//public ResponseEntity<?> listar() {
		//List<Especialidade> especialidades = especialidadeRepository.findAll();
		//return !especialidades.isEmpty() ? ResponseEntity.ok(especialidades) : ResponseEntity.noContent().build();
	//}

}
