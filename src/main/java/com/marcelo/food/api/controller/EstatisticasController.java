package com.marcelo.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.domain.filter.VendaDiariaFilter;
import com.marcelo.food.domain.model.dto.VendaDiaria;
import com.marcelo.food.domain.service.VendaQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {
	
	@Autowired
	private VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria>  consultarVendaDiaria(VendaDiariaFilter vendaDiariaFilter,
			@RequestParam(required = false, defaultValue = "+00:00")  String timeOffSet) {
		return vendaQueryService.consultarVendasDiarias(vendaDiariaFilter,timeOffSet);
	}

}
