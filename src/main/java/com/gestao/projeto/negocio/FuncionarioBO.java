package com.gestao.projeto.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.Funcionario;
import com.gestao.projeto.repository.FuncionarioRepository;

@Component
public class FuncionarioBO {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> listar(){
		return funcionarioRepository.findAll();
	}
	
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	public void apagar(Long id) {
		
		funcionarioRepository.deleteById(id);
	}

}
