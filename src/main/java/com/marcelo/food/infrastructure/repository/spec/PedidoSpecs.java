package com.marcelo.food.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.marcelo.food.domain.filter.PedidoFilter;
import com.marcelo.food.domain.model.Pedido;

public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query, builder) -> {
			// verifica se resultado da query é do tipo pedido, caso contrário não faz
						// fetch, pois pode ser select count() do page.
			if (Pedido.class.equals(query.getResultType())) {
			root.fetch("restaurante").fetch("cozinha");
			root.fetch("usuario");
			}

			var predicates = new ArrayList<Predicate>();

			if (filtro.getUsuarioId() != null) {
				predicates.add(builder.equal(root.get("usuario"), filtro.getUsuarioId()));
			}

			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}

			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}

			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
