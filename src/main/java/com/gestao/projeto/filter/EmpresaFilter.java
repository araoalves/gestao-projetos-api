package com.gestao.projeto.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.gestao.projeto.model.Empresa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpresaFilter  {
	
	private Long empresaId;

	public Specification<Empresa> toSpec() {
		return (root, query, builder) -> {
			
			List<Predicate> predicados = new ArrayList<>();
			
			if(empresaId != null) {
				Path<Long> campoId = root.<Long>get("id");
				Predicate predicadoId = builder.equal(campoId, empresaId);
				predicados.add(predicadoId);
			}			
	
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}


}
