package com.marcelo.food.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.model.FormaPagamento;
import com.marcelo.food.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryJpa implements FormaPagamentoRepository {
	
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> listar() {
		TypedQuery<FormaPagamento> query =  manager.createQuery("from FormaPagamento", FormaPagamento.class);
		return query.getResultList();
	}
	
	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
		
	}
	
	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento fp) {
		return manager.merge(fp);
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento fp) {
		fp = buscar(fp.getId());
		 manager.remove(fp);
	}
	
}
