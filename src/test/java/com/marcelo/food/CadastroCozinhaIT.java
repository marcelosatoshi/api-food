package com.marcelo.food;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.repository.CozinhaRepository;
import com.marcelo.food.util.DatabaseCleaner;
import com.marcelo.food.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {
	
	private static final int COZINHA_ID_INEXISTENTE = 100;


	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner database;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private int quantidadeCozinhasCadastradas;
	private Cozinha cozinhaAmericana;
	private String jsonCorretoCozinhaChinesa;

	
	@BeforeEach
	public void setUp() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port = port;
		
		database.clearTables();
		prepararDados();
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");

	}

	@Test
	void deveRetornarStatus200_QuandoConsultarCozinha() {

		RestAssured.given().accept(ContentType.JSON)
		.when().get().then()
		.statusCode(HttpStatus.OK.value());

	}

	@Test
	void deveConter4Cozinhas_QuandoConsultarCozinha() {

		RestAssured.given().accept(ContentType.JSON)
		.when().get().then()
		.body("", Matchers.hasSize(quantidadeCozinhasCadastradas));
	}
	

	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		
		RestAssured.given().pathParam("id", cozinhaAmericana.getId()).accept(ContentType.JSON)
		.when().get("/{id}").then()
		.statusCode(HttpStatus.OK.value()).body("nome", equalTo(cozinhaAmericana.getNome()));

	}
	
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaInexistente() {
		
		RestAssured.given().pathParam("id", COZINHA_ID_INEXISTENTE).accept(ContentType.JSON)
		.when().get("/{id}").then()
		.statusCode(HttpStatus.NOT_FOUND.value());

	}
	
	
	private void prepararDados() {
		Cozinha cozinhaTailandesa  = new Cozinha();
		cozinhaTailandesa .setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa );
		
		
		 cozinhaAmericana  = new Cozinha();
		cozinhaAmericana .setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana );
		
	     quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();

	}
}
