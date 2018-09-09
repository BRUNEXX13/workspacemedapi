package com.example.med.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Integer> idade;
	public static volatile SingularAttribute<Paciente, Long> codigo;
	public static volatile SingularAttribute<Paciente, Boolean> ativo;
	public static volatile SingularAttribute<Paciente, Endereco> endereco;
	public static volatile SingularAttribute<Paciente, String> cpf;
	public static volatile SingularAttribute<Paciente, String> nome;

}

