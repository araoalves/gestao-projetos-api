package com.gestao.projeto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.projeto.Projection.FrenteTrabalhoGeralProjection;
import com.gestao.projeto.Projection.FrenteTrabalhoResumoProjection;
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
	
	public void apagar(Long id) {
		
		frenteTrabalhoRepository.deleteById(id);
	}
	
	public List<FrenteTrabalho> listarPorEmpresa(Long id) {
		return frenteTrabalhoRepository.buscaFreteTrabalhoEmpresa(id);
	}
	
	public List<FrenteTrabalhoGeralProjection> gerarGeral(Long emp,Long cont){
		
		return frenteTrabalhoRepository.buscaRelatorioGeral(emp,cont);
	
	}
	
public List<FrenteTrabalhoResumoProjection> gerarResumo(Long emp){
		
		return frenteTrabalhoRepository.buscaRelatorioResumo(emp);
	
	}

}
