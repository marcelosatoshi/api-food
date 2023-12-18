package com.marcelo.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.food.domain.exception.PermissaoNaoEncontradaException;
import com.marcelo.food.domain.model.Permissao;
import com.marcelo.food.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	 private PermissaoRepository permissaoRepository;

	public Permissao buscarOuFalhar(Long id ) {
		return permissaoRepository.findById(id).orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}
}
