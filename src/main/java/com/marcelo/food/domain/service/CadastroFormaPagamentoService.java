package com.marcelo.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.exception.EntidadeEmUsoException;
import com.marcelo.food.domain.exception.FormaPagamentoNaoEncontradaException;
import com.marcelo.food.domain.model.FormaPagamento;
import com.marcelo.food.domain.repository.PagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return pagamentoRepository.save(formaPagamento);
	}

	@Transactional
	public void excluir(Long id) {
		try {

			pagamentoRepository.deleteById(id);
            pagamentoRepository.flush();

			
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(MSG_FORMA_PAGAMENTO_EM_USO);
		}
	}

	public FormaPagamento buscarOuFalhar(Long id) {
		return pagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}

}
