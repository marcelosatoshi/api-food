package com.marcelo.food;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marcelo.food.domain.exception.CozinhaNaoEncontradaException;
import com.marcelo.food.domain.exception.EntidadeEmUsoException;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.service.CadastroCozinhaService;
import com.marcelo.food.domain.service.CadastroRestauranteService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {
	
	// Teste de integração
	

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Test
	void testarCadastroCozinhaComSucesso() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Cozinha do Marcelo");

		cozinha = cadastroCozinha.salvar(cozinha);

		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();

	}

	@Test
	void testarCadastroCozinhaSemNome() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinha.salvar(cozinha);
		});

		assertThat(erroEsperado).isNotNull();

	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaEmUso() {

		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinha.excluir((long) 1);
		});

		assertThat(erroEsperado).isNotNull();

	}
	
	
	@Test
	void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
		CozinhaNaoEncontradaException erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
			cadastroCozinha.excluir((long) 20);
		});

		assertThat(erroEsperado).isNotNull();

	}
		
}
