package com.marcelo.food.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marcelo.food.domain.exception.EntidadeEmUsoException;
import com.marcelo.food.domain.exception.EntidadeNaoEncontradaException;
import com.marcelo.food.domain.model.Estado;
import com.marcelo.food.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	public Optional<Estado> buscar(Long id) {
		return estadoRepository.findById(id);
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);

	}

	
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida, pois está em uso" , id));
		}
	}

}
