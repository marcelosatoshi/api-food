package com.marcelo.food.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.GrupoModelAssembler;
import com.marcelo.food.api.model.GrupoModel;
import com.marcelo.food.domain.model.Grupo;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.service.CadastroGrupoService;
import com.marcelo.food.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroGrupoService cadastroGrupoService;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@GetMapping
	public List<GrupoModel> buscarTodosGruposDoUsuario(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		Set<Grupo> grupos = usuario.getGrupos();

		return grupoModelAssembler.toModelList(grupos);

	}

	
	
	@DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirGrupoDoUsuario(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
			cadastroUsuarioService.removerGrupo(usuarioId, grupoId);
		
	}
	
	@PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.adicionarGrupo(usuarioId, grupoId);
    }        

}
