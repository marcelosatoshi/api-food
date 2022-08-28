package com.marcelo.food.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.Permissao;
import com.marcelo.food.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryJpa implements PermissaoRepository {
	
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> listar() {
		TypedQuery<Permissao> query =  manager.createQuery("from Permissao", Permissao.class);
		return query.getResultList();
	}
	
	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
		
	}
	
	@Transactional
	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		 manager.remove(permissao);
	}
	
}
