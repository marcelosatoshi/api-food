package com.marcelo.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcelo.food.api.model.input.FormaPagamentoInput;
import com.marcelo.food.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput FormaPagamentoInput) {
		return modelMapper.map(FormaPagamentoInput, FormaPagamento.class);

	}

	public FormaPagamentoInput toDomainObject(FormaPagamento FormaPagamento) {
		return modelMapper.map(FormaPagamento, FormaPagamentoInput.class);

	}
	
	public void copyToDomainObject(FormaPagamentoInput pagamentoInput, FormaPagamento formaPagamento) {
		modelMapper.map(pagamentoInput, formaPagamento);
	}
}
