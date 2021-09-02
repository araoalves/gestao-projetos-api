package com.gestao.projeto.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.Contrato;
import com.gestao.projeto.repository.ContratoRepository;

@Component
public class ContratoBO {
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	public List<Contrato> listar(){
		return contratoRepository.findAll();
	}
	
	public Contrato salvar(Contrato contrato) {
		return contratoRepository.save(contrato);
	}
	
	public Contrato apagar(Contrato contrato) {
		
		Optional<Contrato> emp = contratoRepository.findById(contrato.getId());
		
		if (emp.isEmpty()) {
			Contrato	emp1 = new Contrato();
			return emp1;
		}else {
			contratoRepository.delete(contrato);
			return emp.get();
		}
		
	}

}
