package com.marcelo.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.CidadeInputDisassembler;
import com.marcelo.food.api.assembler.CidadeModelAssembler;
import com.marcelo.food.api.model.CidadeModel;
import com.marcelo.food.api.model.input.CidadeInput;
import com.marcelo.food.domain.exception.EstadoNaoEncontradaException;
import com.marcelo.food.domain.exception.NegocioException;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisasembler;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel( cadastroCidade.listar());
	}

	@GetMapping("/{id}")
	public CidadeModel buscar(@PathVariable Long id) {
		return cidadeModelAssembler.toModel( cadastroCidade.buscarOuFalhar(id));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisasembler.toDomainObject(cidadeInput);
			cidade = cadastroCidade.salvar(cidade);
			return cidadeModelAssembler.toModel(cidade);
			
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{id}")
	public CidadeModel atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {

		Cidade cidadeBanco = cadastroCidade.buscarOuFalhar(id);

		//BeanUtils.copyProperties(cidade, cidadeBanco, "id");
		cidadeInputDisasembler.copyToDomainObject(cidadeInput, cidadeBanco);
		try {
			return cidadeModelAssembler.toModel( cadastroCidade.salvar(cidadeBanco));
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {

		cadastroCidade.excluir(id);

	}
	
	
	

}