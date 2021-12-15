package com.gestao.projeto.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gestao.projeto.filter.ContratoFilter;
import com.gestao.projeto.model.Contrato;
import com.gestao.projeto.repository.ContratoRepository;

@Component
public class ContratoBO {
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	public Page<Contrato> listar(ContratoFilter filter, Pageable pageble){
		return contratoRepository.findAll(filter.toSpec(), pageble);
	}
	
	public Contrato salvar(Contrato contrato) {
		return contratoRepository.save(contrato);
	}
	
	public void apagar(Long id) {		
		contratoRepository.deleteById(id);		
	}

	public Optional<Contrato> findById(long id) {
		return contratoRepository.findById(id);
	}

}
