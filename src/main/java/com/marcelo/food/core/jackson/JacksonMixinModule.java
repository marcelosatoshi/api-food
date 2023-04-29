package com.marcelo.food.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.marcelo.food.api.model.mixin.CidadeMixin;
import com.marcelo.food.api.model.mixin.CozinhaMixin;
import com.marcelo.food.api.model.mixin.RestauranteMixin;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	    setMixInAnnotation(Cidade.class, CidadeMixin.class);

		
	}
	
	

}
