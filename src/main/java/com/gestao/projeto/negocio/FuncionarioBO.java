package com.gestao.projeto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.Funcionario;
import com.gestao.projeto.repository.FuncionarioRepository;

@Component
public class FuncionarioBO {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Page<Funcionario> listar(Pageable pageble){
		return funcionarioRepository.findAll(pageble);
	}
	
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	public void apagar(Long id) {
		
		funcionarioRepository.deleteById(id);
	}

}
