package com.gestao.projeto.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.FrenteTrabalho;
import com.gestao.projeto.repository.FrenteTrabalhoRepository;

@Component
public class FrenteTrabalhoBO {
	
	@Autowired
	private FrenteTrabalhoRepository frenteTrabalhoRepository;
	
	public List<FrenteTrabalho> listar() {
		return frenteTrabalhoRepository.findAll();
	}
	
	public FrenteTrabalho salvar(FrenteTrabalho frenteTrabalho) {
		return frenteTrabalhoRepository.save(frenteTrabalho);
	}
	
	public FrenteTrabalho apagar(FrenteTrabalho frenteTrabalho) {
		
		Optional<FrenteTrabalho> emp = frenteTrabalhoRepository.findById(frenteTrabalho.getId());
		
		if (emp.isEmpty()) {
			FrenteTrabalho	emp1 = new FrenteTrabalho();
			return emp1;
		}else {
			frenteTrabalhoRepository.delete(frenteTrabalho);
			return emp.get();
		}
		
	}

}
