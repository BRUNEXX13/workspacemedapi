package com.example.med.api.repository.exame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.example.med.api.model.Exame;
import com.example.med.api.model.Exame_;
import com.example.med.api.repository.filter.ExameFilter;

public class ExameRepositoryImpl implements ExameRepositoryQuery {


	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Exame> filtrar(ExameFilter exameFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Exame> criteria = builder.createQuery(Exame.class);
		Root<Exame> root = criteria.from(Exame.class);
		
		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Exame> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ExameFilter exameFilter, CriteriaBuilder builder,
			Root<Exame> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(exameFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.nome)), "%" + exameFilter.getNome().toLowerCase() + "%"));
		}
		
		if (exameFilter.getDataExameDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Exame_.dataExame), exameFilter.getDataExameDe()));
		}
		
		if (exameFilter.getDataExameAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Exame_.dataExame), exameFilter.getDataExameAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}