package com.marcelo.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.marcelo.food.api.assembler.CozinhaInputDisassembler;
import com.marcelo.food.api.assembler.CozinhaModelAssembler;
import com.marcelo.food.api.model.CozinhaModel;
import com.marcelo.food.api.model.input.CozinhaInput;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.repository.CozinhaRepository;
import com.marcelo.food.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisasembler;

	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@GetMapping
	public Page<CozinhaModel> listar(Pageable pageble) {
		Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageble);
		
		List<CozinhaModel> cozinhaModel =  cozinhaModelAssembler.toCollectionModel( cozinhaPage.getContent());
		
		Page<CozinhaModel> cozinhaModelPage = new PageImpl<>(cozinhaModel, pageble, cozinhaPage.getTotalElements());
		return cozinhaModelPage;
	}

	@GetMapping("/{id}")
	public CozinhaModel buscar(@PathVariable Long id) {
		return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(id));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel inserir(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha =	cozinhaInputDisasembler.toDomainObject(cozinhaInput);
		return cozinhaModelAssembler.toModel( cadastroCozinha.salvar(cozinha));
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

//			cozinhaAtual.setNome(cozinha.getNome());
	//	BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		
		 cozinhaInputDisasembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> deletar(@PathVariable Long id) {
		cadastroCozinha.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
