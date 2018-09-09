package com.example.med.api.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exame.class)
public abstract class Exame_ {

	public static volatile SingularAttribute<Exame, Long> codigo;
	public static volatile SingularAttribute<Exame, TipoExame> tipo;
	public static volatile SingularAttribute<Exame, Especialidade> especialidade;
	public static volatile SingularAttribute<Exame, LocalDate> dataExame;
	public static volatile SingularAttribute<Exame, Paciente> paciente;
	public static volatile SingularAttribute<Exame, String> nome;

}

