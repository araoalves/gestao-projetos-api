package com.gestao.projeto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gestao.projeto.Projection.FrenteTrabalhoGeralProjection;
import com.gestao.projeto.Projection.FrenteTrabalhoResumoProjection;
import com.gestao.projeto.model.FrenteTrabalho;
import com.gestao.projeto.repository.FrenteTrabalhoRepository;

@Component
public class FrenteTrabalhoBO {
	
	@Autowired
	private FrenteTrabalhoRepository frenteTrabalhoRepository;
	
	public Page<FrenteTrabalho> listar(Pageable pageble) {
		return frenteTrabalhoRepository.findAll(pageble);
	}
	
	public FrenteTrabalho salvar(FrenteTrabalho frenteTrabalho) {
		return frenteTrabalhoRepository.save(frenteTrabalho);
	}
	
	public void apagar(Long id) {
		
		frenteTrabalhoRepository.deleteById(id);
	}
	
	public Page<FrenteTrabalho> listarPorEmpresa(Long id,Pageable pageble) {
		return frenteTrabalhoRepository.buscaFreteTrabalhoEmpresa(id, pageble);
	}
	
	public FrenteTrabalhoGeralProjection gerarGeral(Long emp,Long cont){
		
		return frenteTrabalhoRepository.buscaRelatorioGeral(emp,cont);
	
	}
	
public FrenteTrabalhoResumoProjection gerarResumo(Long emp){
		
		return frenteTrabalhoRepository.buscaRelatorioResumo(emp);
	
	}

}
