package com.marcelo.food.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.marcelo.food.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoModel {
	
	private String  codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;	
	private BigDecimal valorTotal;	
	private StatusPedido status;	
	private OffsetDateTime  dataCriacao;	
	private OffsetDateTime  dataConfirmacao;
	private OffsetDateTime  dataCancelamento;
	private OffsetDateTime  dataEntrega;
	private FormaPagamentoModel formaPagamento;
	private RestauranteResumoModel restaurante;
	private UsuarioModel usuario;
	private EnderecoModel endereco;
	private  List<ItemPedidoModel>  itens;

}
