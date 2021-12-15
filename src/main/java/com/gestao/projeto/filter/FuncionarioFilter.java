package com.gestao.projeto.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.gestao.projeto.model.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuncionarioFilter  {
	
	private Long empresaId;
	private Long funcionarioId;

	public Specification<Funcionario> toSpec() {
		return (root, query, builder) -> {
			
			List<Predicate> predicados = new ArrayList<>();
			
			if(empresaId != null) {
				Path<Long> campoId = root.<Long>get("empresa").get("id");
				Predicate predicadoId = builder.equal(campoId, empresaId);
				predicados.add(predicadoId);
			}	
			
			if(funcionarioId != null) {
				Path<Long> campoFuncionarioId = root.<Long>get("id");
				Predicate predicadoFuncionarioId = builder.equal(campoFuncionarioId, funcionarioId);
				predicados.add(predicadoFuncionarioId);
			}	
	
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}


}
