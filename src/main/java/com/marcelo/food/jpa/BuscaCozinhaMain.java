package com.marcelo.food.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.marcelo.food.ApifoodApplication;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(
				ApifoodApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cd = ((BeanFactory) applicationContext).getBean(CozinhaRepository.class);
		
		Cozinha cozinha = cd.buscar(1L);
		System.out.println(cozinha.getNome());
		
	

	}
}
