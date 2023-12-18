package com.marcelo.food.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcelo.food.api.model.RestauranteModel;
import com.marcelo.food.api.model.input.ItemPedidoInput;
import com.marcelo.food.domain.model.ItemPedido;
import com.marcelo.food.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {
	
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete)
		.addMapping(enderecoSrc -> enderecoSrc.getEndereco().getCidade().getEstado().getNome(), (enderecoModelDestino,value) -> enderecoModelDestino.getEndereco().getCidade().setEstado((String) value));
	
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class).addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		return modelMapper;
	}
	
	

}
