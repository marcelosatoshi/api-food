package com.marcelo.food.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.marcelo.food.ApifoodApplication;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.repository.CozinhaRepository;

public class InclusaoConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(
				ApifoodApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cd = ((BeanFactory) applicationContext).getBean(CozinhaRepository.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");

		Cozinha cozinha2 = new Cozinha();
		cozinha1.setNome("Japonesa");
		
		cd.salvar(cozinha1);
		cd.salvar(cozinha2);
		
	

	}
}
