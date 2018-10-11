package com.example.med.api.repository.projection;

import java.time.LocalDate;

import com.example.med.api.model.TipoExame;


//Classe De projecoes//
//Retorna apenas o que eu quero no resumo  do Paciente , seria um List mas apenas com o retorno que desejo
public class ResumoExame {

	private Long codigo;
	private String nome;
	private LocalDate dataExame;
	private TipoExame tipo;
	private String especialidade;
	private String paciente;

	public ResumoExame(Long codigo, String nome, LocalDate dataExame, TipoExame tipo, String especialidade,
			String paciente) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataExame = dataExame;
		this.tipo = tipo;
		this.especialidade = especialidade;
		this.paciente = paciente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoExame getTipo() {
		return tipo;
	}

	public void setTipo(TipoExame tipo) {
		this.tipo = tipo;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public LocalDate getDataExame() {
		return dataExame;
	}

	public void setDataExame(LocalDate dataExame) {
		this.dataExame = dataExame;
	}

}
