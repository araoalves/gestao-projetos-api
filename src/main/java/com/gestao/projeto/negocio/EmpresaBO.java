package com.gestao.projeto.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.projeto.model.Empresa;
import com.gestao.projeto.repository.EmpresaRepository;

@Component
public class EmpresaBO {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}
	
	public Empresa salvar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	public void apagar(Long id) {
		
		empresaRepository.deleteById(id);
	}

}
