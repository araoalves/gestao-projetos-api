package com.gestao.projeto.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestao.projeto.model.Contrato;

public interface ContratoRepository extends PagingAndSortingRepository<Contrato, Long> , JpaSpecificationExecutor<Contrato>{

}
