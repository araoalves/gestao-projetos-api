package com.gestao.projeto.filter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.gestao.projeto.model.FrenteTrabalho;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FrentetrabalhoFilter  {
	
	private Long funcionarioId;
	private Long empresaId;
	private Date dataInicioData;
	private Date dadaFimData;
	private String dataFiltro;
	
	public Specification<FrenteTrabalho> toSpec() {
		return (root, query, builder) -> {
			
			List<Predicate> predicados = new ArrayList<>();
			
			if(funcionarioId != null) {
				Path<Long> campoId = root.<Long>get("funcionario").get("id");
				Predicate predicadoId = builder.equal(campoId, funcionarioId);
				predicados.add(predicadoId);
			}
			
			if(empresaId != null) {
				Path<Long> campoEmpresaId = root.<Long>get("empresa").get("id");
				Predicate predicadoEmpresaId = builder.equal(campoEmpresaId, empresaId);
				predicados.add(predicadoEmpresaId);
			}
			
			if(dataFiltro != null) {
				if(dataFiltro.equals("I")) {
					
					if(dataInicioData != null || dadaFimData != null) {
						Path<Date> campoId1 = root.<Date>get("dataInicio");
						Predicate predicadoId = builder.between(campoId1,dataInicioData,dadaFimData);
						predicados.add(predicadoId);
					}
					
				}else if (dataFiltro.equals("F")) {
					
					if(dataInicioData != null || dadaFimData != null) {
						Path<Date> campoId = root.<Date>get("dadaFim");
						Predicate predicadoId = builder.between(campoId,dataInicioData,dadaFimData);
						predicados.add(predicadoId);
					}
					
				} 
			}
	
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}

}
