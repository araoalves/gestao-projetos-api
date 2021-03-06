package com.gestao.projeto.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gestao.projeto.filter.EmpresaFilter;
import com.gestao.projeto.model.Empresa;
import com.gestao.projeto.repository.EmpresaRepository;

@Component
public class EmpresaBO {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Page<Empresa> listar(EmpresaFilter filter, Pageable pageble) {
		return empresaRepository.findAll(filter.toSpec(),pageble);
	}
	
	public Empresa salvar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	public void apagar(Long id) {
		
		empresaRepository.deleteById(id);
	}

	public Optional<Empresa> findById(long id) {
		return empresaRepository.findById(id);
	}

}
