package com.marcelo.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.marcelo.food.api.assembler.PedidoInputDisassembler;
import com.marcelo.food.api.assembler.PedidoModelAssembler;
import com.marcelo.food.api.assembler.PedidoResumoModelAssembler;
import com.marcelo.food.api.model.PedidoModel;
import com.marcelo.food.api.model.PedidoResumoModel;
import com.marcelo.food.api.model.input.PedidoInput;
import com.marcelo.food.core.data.PageableTranslator;
import com.marcelo.food.domain.exception.EntidadeNaoEncontradaException;
import com.marcelo.food.domain.exception.NegocioException;
import com.marcelo.food.domain.filter.PedidoFilter;
import com.marcelo.food.domain.model.Pedido;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.repository.PedidoRepository;
import com.marcelo.food.domain.service.EmissaoPedidoService;
import com.marcelo.food.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	/*
	 * @GetMapping public MappingJacksonValue listar(@RequestParam(required = false)
	 * String campos) { List<Pedido> pedidos = pedidoRepository.findAll();
	 * List<PedidoResumoModel> pedidosModel =
	 * pedidoResumoModelAssembler.toCollectionModel(pedidos);
	 * 
	 * MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
	 * 
	 * SimpleFilterProvider filterProvider = new SimpleFilterProvider();
	 * filterProvider.addFilter("pedidoFilter",
	 * SimpleBeanPropertyFilter.serializeAll());
	 * 
	 * if (StringUtils.isNotBlank(campos)) {
	 * filterProvider.addFilter("pedidoFilter",
	 * SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(","))); }
	 * 
	 * pedidosWrapper.setFilters(filterProvider);
	 * 
	 * return pedidosWrapper; }
	 */
	@GetMapping
	public Page<PedidoResumoModel> listar(PedidoFilter filtro, Pageable pageble) {
		
		pageble = traduzirPageble(pageble);
		
		Page<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageble);
		List<PedidoResumoModel> pedidoResumoModel = pedidoResumoModelAssembler
				.toCollectionModel(todosPedidos.getContent());
		
		Page<PedidoResumoModel> pagePedidoResumo = new PageImpl<>(pedidoResumoModel, pageble, todosPedidos.getTotalElements());
		
		return pagePedidoResumo;

	}

	@GetMapping("/{codigoId}")
	public PedidoModel buscar(@PathVariable String codigoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoId);

		return pedidoModelAssembler.toModel(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {

		try {
			Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			pedido.setUsuario(new Usuario());
			pedido.getUsuario().setId(1L);

			emissaoPedido.emitir(pedido);

			return pedidoModelAssembler.toModel(pedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);

		}

	}
	
	private Pageable traduzirPageble(Pageable pageable) {
		var mapeamentos = ImmutableMap.of("codigo", "codigo",
				"restaurante.nome" , "restaurante.nome",
				"nomeCliente", "usuario.nome",
				"valorTotal", "valorTotal");
		
		return PageableTranslator.translate(pageable, mapeamentos);
	}
}