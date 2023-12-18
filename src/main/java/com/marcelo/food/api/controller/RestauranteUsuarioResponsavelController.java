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

import com.marcelo.food.api.assembler.UsuarioModelAssembler;
import com.marcelo.food.api.model.UsuarioModel;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private CadastroRestauranteService cadsCadastroRestauranteService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@GetMapping
	public List<UsuarioModel> buscarUsuarioResponsais(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadsCadastroRestauranteService.buscarOuFalhar(restauranteId);
		Set<Usuario> usuarioResponsaveis =  restaurante.getUsuariosResponsaveis();

		return usuarioModelAssembler.toCollectionModel(usuarioResponsaveis);

	}
	
	@DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void  desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadsCadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
	}
	
	@PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void  associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadsCadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
	}

}