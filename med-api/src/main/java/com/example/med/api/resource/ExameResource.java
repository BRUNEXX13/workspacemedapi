	package com.example.med.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.med.api.event.RecursoCriadoEvent;
import com.example.med.api.exceptionhandler.MedApiExceptionHandler.Erro;
import com.example.med.api.model.Exame;
import com.example.med.api.repository.ExameRepository;
import com.example.med.api.repository.filter.ExameFilter;
import com.example.med.api.repository.projection.ResumoExame;
import com.example.med.api.service.ExameService;
import com.example.med.api.service.exception.PacienteInexistenteOuInativaException;

/*
 *  Rest e trabalhado Atraves de Recursos ( PUT < POST < DELETE )
 *  Essa classe vai expor tudo que esta relacionado com as anotações abaixo:/
 */


@RestController
@RequestMapping("/exames")
public class ExameResource {

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ExameService exameService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXAME') and #oauth2.hasScope('read')")
	public Page<Exame> pesquisar(ExameFilter exameFilter, Pageable pageable) {
		return exameRepository.filtrar(exameFilter, pageable);

	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXAME') and #oauth2.hasScope('read')")
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable) {
		return exameRepository.resumir(exameFilter, pageable);

	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXAME') and #oauth2.hasScope('read')")
	public ResponseEntity<Exame> buscarPeloCodigo(@PathVariable Long codigo) {
		Exame exame = exameRepository.findOne(codigo);
		return exame != null ? ResponseEntity.ok(exame) : ResponseEntity.notFound().build();
	}

	// @Salvar uma Uma Especialidade no Banco De Dados - Status Code 201
	// Created//
	// Criando Valores através do JSON @Valid Bean Validator
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EXAME') and #oauth2.hasScope('write')")
	public ResponseEntity<Exame> criar(@Valid @RequestBody Exame exame, HttpServletResponse response) {
		Exame exameSalvo = exameRepository.save(exame);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, exameSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(exameSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_EXAME') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		exameRepository.delete(codigo);
	}

	/*
	 * @PutMapping("/{codigo}")
	 * 
	 * @PreAuthorize("hasAuthority('ROLE_CADASTRAR_EXAME') and #oauth2.hasScope('write')"
	 * ) public ResponseEntity<Exame> atualizar(@PathVariable Long
	 * codigo, @Valid @RequestBody Exame exame) { Exame exameSalvo =
	 * exameService.atualizar(codigo, exame); return
	 * ResponseEntity.ok(exameSalvo); }
	 */

	// Passando uma Excecao Especifica // Naoo pode salvar um exame com paciente inativo
	//
	@ExceptionHandler({ PacienteInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PacienteInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("paciente.inexistente-ou-inativa", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EXAME')")
	public ResponseEntity<Exame> atualizar(@PathVariable Long codigo, @Valid @RequestBody Exame exame) {
		try {
			Exame exameSalvo = exameService.atualizar(codigo, exame);
			return ResponseEntity.ok(exameSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
