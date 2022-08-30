package com.marcelo.food.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marcelo.food.domain.model.FormaPagamento;

@Repository
public interface PagamentoRepository {

	List<FormaPagamento> listar();

	FormaPagamento buscar(Long id);

	FormaPagamento salvar(FormaPagamento fp);

	void remover(FormaPagamento fp);

}
