package com.example.med.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "exame")
public class Exame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;

	@NotNull
	@Size(min = 3, max = 50)
	private String nome;

	@NotNull
	@Column(name = "data_exame")
	private LocalDate dataExame;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoExame tipo;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "codigo_especialidade")
	private Especialidade especialidade;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataExame() {
		return dataExame;
	}

	public void setDataExame(LocalDate dataExame) {
		this.dataExame = dataExame;
	}

	public TipoExame getTipo() {
		return tipo;
	}

	public void setTipo(TipoExame tipo) {
		this.tipo = tipo;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame(long codigo, String nome, LocalDate dataExame, TipoExame tipo, Especialidade especialidade,
			Paciente paciente) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataExame = dataExame;
		this.tipo = tipo;
		this.especialidade = especialidade;
		this.paciente = paciente;
	}

	public Exame() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
