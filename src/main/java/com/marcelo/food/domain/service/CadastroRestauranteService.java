package com.marcelo.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.exception.RestauranteNaoEncontradaException;
import com.marcelo.food.domain.model.Cidade;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.FormaPagamento;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long enderecoCidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		Cidade cidade = cadastroCidadeService.buscarOuFalhar(enderecoCidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public void adicionarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

		restaurante.getFormasPagamentos().add(formaPagamento);
	}

	@Transactional
	public void removerFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

		restaurante.getFormasPagamentos().remove(formaPagamento);
	}

	@Transactional
	public void ativo(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);

		restaurante.setAtivo(true);
	}

	@Transactional
	public void inativo(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);
		restaurante.setAtivo(false);

	}
	
	@Transactional
	public void ativar(List<Long> restauranteId) {
		restauranteId.forEach(this::ativo);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteId) {
		restauranteId.forEach(this::inativo);
	}


	@Transactional
	public void abrir(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);

		restaurante.abrir();
	}

	@Transactional
	public void fechar(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);

		restaurante.fechar();
	}

	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		restaurante.adicionarResponsavel(usuario);

	}
	

	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		restaurante.removerResponsavel(usuario);

	}


	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradaException(
				String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}
}
