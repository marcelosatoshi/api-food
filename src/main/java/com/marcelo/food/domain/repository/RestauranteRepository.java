package com.marcelo.food.domain.repository;

import java.util.List;

import com.marcelo.food.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();

	Restaurante buscar(Long id);

	Restaurante salvar(Restaurante fp);

	void remover(Long id);

}
