package com.gestao.projeto.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestao.projeto.model.Empresa;

public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa>{

}
