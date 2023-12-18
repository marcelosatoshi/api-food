package com.marcelo.food.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelo.food.domain.model.FormaPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<FormaPagamento, Long> {


}
