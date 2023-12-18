package com.marcelo.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.exception.NegocioException;
import com.marcelo.food.domain.exception.PedidoNaoEncontradoException;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.model.FormaPagamento;
import com.marcelo.food.domain.model.Pedido;
import com.marcelo.food.domain.model.Produto;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    
    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;
    
    @Autowired
    private CadastroUsuarioService cdasCadastroUsuarioService;
    
    @Autowired
    private CadastroCidadeService cadastrocidadeService;
    
    @Autowired
    private CadastroProdutoService cadastroProduto;
    
    public Pedido buscarOuFalhar(String codigoId) {
        return pedidoRepository.findByCodigoWithEagerFetch(codigoId)
            .orElseThrow(() -> new PedidoNaoEncontradoException(codigoId));
    }            
    
    @Transactional
    public void  emitir(Pedido pedido) {
    	validarPedido(pedido);
    	validarItemPedido(pedido);
    	
    	pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
    	pedido.calcularValorTotal();
    	
    	pedidoRepository.save(pedido);
    	
    }
    
    
    public void validarPedido(Pedido pedido) {
    	Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
    	FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
    	Usuario usuario =  cdasCadastroUsuarioService.buscarOuFalhar(pedido.getUsuario().getId());
    	Cidade cidade = cadastrocidadeService.buscarOuFalhar(pedido.getEndereco().getCidade().getId());
    	
    	
    	pedido.setRestaurante(restaurante);
    	pedido.setFormaPagamento(formaPagamento);
    	pedido.setUsuario(usuario);
    	pedido.getEndereco().setCidade(cidade);
    	
    	if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
    		throw new NegocioException(String.format("forma de pagamento '%s' não é aceita por esse restaurante", formaPagamento.getDescricao()));
    	}
    	
    	
    }
    
    public void validarItemPedido(Pedido pedido) {
    		pedido.getItens().forEach(item -> {
    			Produto produto = cadastroProduto.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
    		
    			item.setPedido(pedido);
    			item.setProduto(produto);
    			item.setPrecoUnitario(produto.getPreco());	
    				
    		});
    }
}     