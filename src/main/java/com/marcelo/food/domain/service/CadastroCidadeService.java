package com.marcelo.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marcelo.food.domain.exception.EntidadeEmUsoException;
import com.marcelo.food.domain.exception.EntidadeNaoEncontradaException;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.model.Estado;
import com.marcelo.food.domain.repository.CidadeRepository;
import com.marcelo.food.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> listar() {
		return cidadeRepository.listar();

	}

	public Cidade buscar(Long id) {
		return cidadeRepository.buscar(id);
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);

		if (estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de estado em código %d", estadoId));
		}

		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser removida, pois está em uso" , id));
		}
	}
	
	
}
