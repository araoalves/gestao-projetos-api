package com.gestao.projeto.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "frente_trabalho")
public class FrenteTrabalho {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Geração do ID de Frente de Trabalho")
	private Long id;
	
	@Column(name = "data_inicio")
	private Date dataInicio;
	@Column(name = "data_fim")
	private Date dataFim; 
	@Column(name = "descricao_atividades", length=150)
	private String descricaoAtividade;
	@Column(name = "quantidade_horas", length=5)
	private int quantidadeHoras;
	@Column(name = "status", length=1)
	private String status;
	
	@ManyToOne
	@JoinColumn(name="funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="contrato")
	private Contrato contrato;
	
	@Column(name = "data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="empresa")
	private Empresa empresa;

}