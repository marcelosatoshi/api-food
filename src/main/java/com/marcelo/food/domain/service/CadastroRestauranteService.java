package com.marcelo.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.food.domain.exception.EntidadeNaoEncontradaException;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.repository.CozinhaRepository;
import com.marcelo.food.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha  em codigo %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

}
