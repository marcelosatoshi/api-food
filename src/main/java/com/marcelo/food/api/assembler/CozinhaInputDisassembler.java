package com.marcelo.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcelo.food.api.model.input.CozinhaInput;
import com.marcelo.food.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);

	}

	public CozinhaInput toDomainObject(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaInput.class);

	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		
		
				
		modelMapper.map(cozinhaInput, cozinha);
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