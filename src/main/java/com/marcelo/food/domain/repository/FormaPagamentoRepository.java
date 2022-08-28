package com.marcelo.food.domain.repository;

import java.util.List;

import com.marcelo.food.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();

	FormaPagamento buscar(Long id);

	FormaPagamento salvar(FormaPagamento fp);

	void remover(FormaPagamento fp);

}
