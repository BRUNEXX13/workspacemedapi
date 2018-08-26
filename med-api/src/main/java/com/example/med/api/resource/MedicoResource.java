package com.example.med.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.med.api.model.Medico;
import com.example.med.api.repository.MedicoRepository;

//Pegando de Repository 


@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	@Autowired
	MedicoRepository medicoRepository;

	// Listando By Json
	@GetMapping
	public List<Medico> listarMedico() {
		return medicoRepository.findAll();
	}

	// @ CRIAR Uma Especialidade - Status Code 201 Created //
	// Criando Valors através do JSON @Valid Bean Validator

	@PostMapping                        //@Valid Ativa o  bean Validation
	public ResponseEntity<Medico> criar(@Valid @RequestBody Medico medico, HttpServletResponse response) {
		Medico medicoSalvo = medicoRepository.save(medico);

		// Devolvendo o valor criado para o json
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/[codigo]")
		.buildAndExpand(medicoSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		// Apos ser Criado um médico, recebe o codigo criado //
		// @ResponseStatus(HttpStatus.CREATED), Retornando os valores da API;
		return ResponseEntity.created(uri).body(medicoSalvo);
		// Devolve o valor no Json pro desenvolvedor 

	}

	// Buscando a Medico pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Medico> buscarPeloCodigo(@PathVariable Long codigo) {
		Medico medico = medicoRepository.findOne(codigo);
	
		
		// Caso a Especialidade seja Nula ele retorna uma 404 Error
	return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();

		}
	}
