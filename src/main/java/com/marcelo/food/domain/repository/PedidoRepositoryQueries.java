package com.marcelo.food.domain.repository;

import java.util.Optional;

import com.marcelo.food.domain.model.Pedido;

public interface PedidoRepositoryQueries {
	
	Optional<Pedido> findByCodigoWithEagerFetch(String codigo);


}
