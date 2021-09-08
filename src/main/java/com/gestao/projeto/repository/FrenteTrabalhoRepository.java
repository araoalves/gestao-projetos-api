package com.gestao.projeto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestao.projeto.Projection.FrenteTrabalhoGeralProjection;
import com.gestao.projeto.Projection.FrenteTrabalhoResumoProjection;
import com.gestao.projeto.model.FrenteTrabalho;

public interface FrenteTrabalhoRepository extends JpaRepository<FrenteTrabalho, Long>{
	
	@Query(value = "SELECT * from frente_trabalho t where t.empresa = ?1", nativeQuery = true)
	Page<FrenteTrabalho> buscaFreteTrabalhoEmpresa (Long id, Pageable pageble);
	
	@Query(value ="SELECT "
			+ "f.nome as nome,"
			+ "SUM(t.quantidade_horas) as horasTrabalhadas,"
			+ "cast(SUM((t.quantidade_horas * c.valor_hora)) as float) as valorPagar "
			+ "FROM frente_trabalho t "
			+ "INNER JOIN funcionario f on f.id = t.funcionario "
			+ "INNER JOIN contrato c on c.id = t.contrato "
			+ "WHERE t.empresa = ? "
			+ "AND t.contrato = ? "
			+ "AND t.status = 'C' "
			+ "group by f.nome", nativeQuery = true)
	FrenteTrabalhoGeralProjection buscaRelatorioGeral (Long emp, Long cont);
	
	
	
	@Query(value = "SELECT "
			+ "c.quantidade  as bancoHoras,"
			+ "SUM(t.quantidade_horas) as horasTrabalhadas,"
			+ "c.quantidade - SUM(t.quantidade_horas) horasAberto "
			+ "FROM frente_trabalho t "
			+ "INNER JOIN empresa e on e.id = t.empresa "
			+ "INNER JOIN contrato c on c.id = t.contrato "
			+ "WHERE t.empresa = ? "
			+ "group by c.quantidade", nativeQuery = true)
	FrenteTrabalhoResumoProjection buscaRelatorioResumo (Long emp);
	
}
