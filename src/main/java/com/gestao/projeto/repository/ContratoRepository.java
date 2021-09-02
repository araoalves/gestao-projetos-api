package com.gestao.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.projeto.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
