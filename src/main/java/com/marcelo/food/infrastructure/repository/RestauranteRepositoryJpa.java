package com.marcelo.food.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryJpa implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);
		return query.getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);

	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurente) {
		return manager.merge(restaurente);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Restaurante restaurante = buscar(id);
		if (restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(id);
	}

}
