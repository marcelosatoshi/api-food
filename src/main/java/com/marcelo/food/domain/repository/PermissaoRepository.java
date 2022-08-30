package com.marcelo.food.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marcelo.food.domain.model.Permissao;

@Repository
public interface PermissaoRepository {

	List<Permissao> listar();

	Permissao buscar(Long id);

	Permissao salvar(Permissao fp);

	void remover(Permissao fp);

}
