package com.marcelo.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.GrupoInputDisassembler;
import com.marcelo.food.api.assembler.GrupoModelAssembler;
import com.marcelo.food.api.model.GrupoModel;
import com.marcelo.food.api.model.input.GrupoInput;
import com.marcelo.food.domain.model.Grupo;
import com.marcelo.food.domain.repository.GrupoRepository;
import com.marcelo.food.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController {

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private CadastroGrupoService grupoService;

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping
	public List<GrupoModel> listar() {
		return grupoModelAssembler.toModelList(grupoRepository.findAll());

	}

	@GetMapping("/{id}")
	public GrupoModel buscar(@PathVariable Long id) {
		return grupoModelAssembler.toModel(grupoService.buscarOuFalhar(id));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel inserir(@RequestBody GrupoInput grupoInput) {

		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		grupo = grupoService.salvar(grupo);

		return grupoModelAssembler.toModel(grupo);

	}
	

	@PutMapping("/{id}")
	public GrupoModel atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		
		Grupo grupo = grupoService.buscarOuFalhar(id);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
		
		grupo = grupoService.salvar(grupo);
		return grupoModelAssembler.toModel(grupo);
	}
	
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		grupoService.deletar(id);
	}

}
