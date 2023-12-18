package com.marcelo.food.domain.service;

import java.util.List;

import com.marcelo.food.domain.filter.VendaDiariaFilter;
import com.marcelo.food.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
	
}