package com.marcelo.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.ProdutoInputDisassembler;
import com.marcelo.food.api.assembler.ProdutoModelAssembler;
import com.marcelo.food.api.model.ProdutoModel;
import com.marcelo.food.api.model.input.ProdutoInput;
import com.marcelo.food.domain.model.Produto;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.repository.ProdutoRepository;
import com.marcelo.food.domain.service.CadastroProdutoService;
import com.marcelo.food.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@GetMapping
	public List<ProdutoModel> listarRestauranteProduto(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		List<Produto> todosProdutos = null;

		if (incluirInativos) {
			todosProdutos = produtoRepository.findByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);

		}

		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		return produtoModelAssembler.toModel(produto);
	}

	@GetMapping(params = "ativos")
	public List<ProdutoModel> listarSomenteAtivos(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos = produtoRepository.findByRestauranteAndAtivo(restaurante, true);

		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}

	@GetMapping(params = "inativos")
	public List<ProdutoModel> listarSomenteInativos(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos = produtoRepository.findByRestauranteAndAtivo(restaurante, false);

		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel inserirProdutoEmRestaurante(@RequestBody @Valid ProdutoInput produtoInput,
			@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);

		produto.setRestaurante(restaurante);

		produto = cadastroProduto.salvar(produto);

		return produtoModelAssembler.toModel(produto);

	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizarProdutoEmRestaurante(@PathVariable Long produtoId, @PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produto);

		produto = cadastroProduto.salvar(produto);

		return produtoModelAssembler.toModel(produto);

	}

}
