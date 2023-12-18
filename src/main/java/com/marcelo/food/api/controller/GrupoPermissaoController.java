package com.marcelo.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.PermissaoModelAssembler;
import com.marcelo.food.api.model.PermissaoModel;
import com.marcelo.food.domain.model.Grupo;
import com.marcelo.food.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissao")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping
	public List<PermissaoModel> buscarPermissaoNoGrupo(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	
	@PutMapping("/{permissaoId}")
	public void associar(@PathVariable Long grupoId ,@PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
	}
	

	@DeleteMapping("/{permissaoId}")
	public void disassociar(@PathVariable Long grupoId ,@PathVariable Long permissaoId) {
		cadastroGrupoService.disassociarPermissao(grupoId, permissaoId);
	}
}
