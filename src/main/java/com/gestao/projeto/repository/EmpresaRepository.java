package com.gestao.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.projeto.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
