package com.marcelo.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcelo.food.api.model.input.RestauranteInput;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);

	}

	public RestauranteInput toDomainObject(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteInput.class);

	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
				// com.marcelo.food.domain.model.Cozinha was altered from 1 to 2
				restaurante.setCozinha(new Cozinha());
				
				if (restaurante.getEndereco() != null) {
					restaurante.getEndereco().setCidade(new Cidade());
				}
				
		modelMapper.map(restauranteInput, restaurante);
	}

	// convertendo na unha..

	/*
	 * public Restaurante toDomainObject(RestauranteInput restauranteInput) {
	 * 
	 * Restaurante restaurante = new Restaurante();
	 * restaurante.setNome(restauranteInput.getNome());
	 * restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
	 * 
	 * Cozinha cozinha = new Cozinha();
	 * cozinha.setId(restauranteInput.getCozinha().getId());
	 * 
	 * restaurante.setCozinha(cozinha);
	 * 
	 * return restaurante; }
	 * 
	 * public RestauranteInput toDomainObject(Restaurante restaurante) {
	 * RestauranteInput restauranteInput = new RestauranteInput();
	 * restauranteInput.setNome(restaurante.getNome());
	 * restauranteInput.setTaxaFrete(restaurante.getTaxaFrete());
	 * 
	 * CozinhaIdInput cozinha = new CozinhaIdInput();
	 * cozinha.setId(restauranteInput.getCozinha().getId());
	 * 
	 * restauranteInput.setCozinha(cozinha);
	 * 
	 * return restauranteInput; }
	 */

}