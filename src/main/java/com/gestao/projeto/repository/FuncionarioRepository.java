package com.gestao.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
