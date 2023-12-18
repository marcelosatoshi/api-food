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

import com.marcelo.food.api.assembler.EstadoInputDisassembler;
import com.marcelo.food.api.assembler.EstadoModelAssembler;
import com.marcelo.food.api.model.EstadoModel;
import com.marcelo.food.api.model.input.EstadoInput;
import com.marcelo.food.domain.model.Estado;
import com.marcelo.food.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@GetMapping
	public List<EstadoModel> listarEstado() {
		return estadoModelAssembler.toCollectionModel(cadastroEstadoService.listar());
	}

	@GetMapping("/{id}")
	public EstadoModel buscar(@PathVariable Long id) {
		return estadoModelAssembler.toModel(cadastroEstadoService.buscarOuFalhar(id));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel inserir(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estado));
	}

	@PutMapping("/{id}")
	public EstadoModel atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtualizado = cadastroEstadoService.buscarOuFalhar(id);

		//BeanUtils.copyProperties(estado, estadoBanco, "id");
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtualizado);

		return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtualizado));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		cadastroEstadoService.excluir(id);
		return ResponseEntity.noContent().build();

	}
}
