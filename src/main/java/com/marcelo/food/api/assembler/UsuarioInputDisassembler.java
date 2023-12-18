package com.marcelo.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcelo.food.api.model.input.UsuarioInput;
import com.marcelo.food.api.model.input.UsuarioInputComSenha;
import com.marcelo.food.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInputComSenha usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	
	public void copyToDomainObject(UsuarioInput usuarioInput ,Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
	
	
	
	}


