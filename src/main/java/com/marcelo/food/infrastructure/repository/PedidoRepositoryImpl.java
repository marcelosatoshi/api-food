package com.marcelo.food.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.marcelo.food.domain.model.Pedido;
import com.marcelo.food.domain.repository.PedidoRepositoryQueries;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQueries {

	private static final String FIND_BY_ID_WITH_EAGER_FETCH_QUERY = "SELECT p FROM Pedido p " +
            "JOIN FETCH p.usuario " +
            "JOIN FETCH p.restaurante r " +
            "JOIN FETCH r.cozinha " +
            "JOIN FETCH p.endereco.cidade c " +
            "JOIn FETCH c.estado " +
            "JOIN FETCH p.formaPagamento " +
            "WHERE p.codigo = :codigo";
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Pedido> findByCodigoWithEagerFetch(String codigo) {
		TypedQuery<Pedido> query = entityManager
                .createQuery(FIND_BY_ID_WITH_EAGER_FETCH_QUERY, Pedido.class)
                .setParameter("codigo", codigo);
		
                 try {
                	 
                 
                	 return Optional.of(query.getSingleResult());
                 } catch (NoResultException e) {
                     return Optional.empty();

	}
		
	}
}
