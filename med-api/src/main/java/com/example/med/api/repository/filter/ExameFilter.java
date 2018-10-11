package com.example.med.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

// Classe Pesquisar por filtros de Exames
// Parametros que desejo passar para realizar as buscas

public class ExameFilter {

	// Pequisa por Descricao
	private String nome;

	// Pesquisa pro data
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataExameDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataExameAte;

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataExameDe() {
		return dataExameDe;
	}

	public void setDataExameDe(LocalDate dataExameDe) {
		this.dataExameDe = dataExameDe;
	}

	public LocalDate getDataExameAte() {
		return dataExameAte;
	}

	public void setDataExameAte(LocalDate dataExameAte) {
		this.dataExameAte = dataExameAte;
	}

}
