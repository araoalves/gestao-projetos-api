package com.gestao.projeto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.Contrato;
import com.gestao.projeto.repository.ContratoRepository;

@Component
public class ContratoBO {
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	public Page<Contrato> listar(Pageable pageble){
		return contratoRepository.findAll(pageble);
	}
	
	public Contrato salvar(Contrato contrato) {
		return contratoRepository.save(contrato);
	}
	
	public void apagar(Long id) {
		
		contratoRepository.deleteById(id);
		
	}

}
