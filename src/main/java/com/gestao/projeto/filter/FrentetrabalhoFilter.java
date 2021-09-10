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
	private Date dataInicio;
	private Date dadaFim;
	
	
	
	public Specification<FrenteTrabalho> toSpec() {
		return (root, query, builder) -> {
			
			List<Predicate> predicados = new ArrayList<>();
			
			if(funcionarioId != null) {
				Path<Long> campoId = root.<Long>get("Funcionario").get("id");
				Predicate predicadoId = builder.equal(campoId, funcionarioId);
				predicados.add(predicadoId);
			}
			
			if(dataInicio != null) {
				Path<Long> campoId = root.<Date>get("FrenteTrabalho").get("dataInicio");
				Predicate predicadoId = builder.equal(campoId, dataInicio);
				predicados.add(predicadoId);
			}
			
			if(dadaFim != null) {
				Path<Long> campoId = root.<Date>get("FrenteTrabalho").get("dataFim");
				Predicate predicadoId = builder.equal(campoId, dadaFim);
				predicados.add(predicadoId);
			}
			
						
			predicados.add(builder.in(root.get("status")).value("A").value("P").value("Q"));	

			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}


}
