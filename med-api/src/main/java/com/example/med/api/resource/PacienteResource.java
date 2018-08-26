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

import com.example.med.api.model.Paciente;
import com.example.med.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	PacienteRepository pacienteRepository;

	// Listando By Json
	@GetMapping
	public List<Paciente> listarPaciente() {
		return pacienteRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		Paciente pacienteSalvo = pacienteRepository.save(paciente);

		// Devolvendo o valor criado para o json
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/[codigo]")
				.buildAndExpand(paciente.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		// Apos ser Criado um médico, recebe o codigo criado //
		// @ResponseStatus(HttpStatus.CREATED), Retornando os valores da API;
		return ResponseEntity.created(uri).body(paciente);
		// Devolve o valor no Json pro desenvolvedor

	}
	
	// Buscando a Especialidade pelo codigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		Paciente paciente = 	pacienteRepository.findOne(codigo);
		
	// Caso a Especialidade seja Nula ele retorna uma 404 Error
		return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();

	}

}
