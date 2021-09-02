package com.gestao.projeto.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "contrato")
public class Contrato {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Geração do ID de contrato")
    private long id;
	
	@Column(name = "descricao", length=150)
	private String descricao;
	
	@Column(name = "quantidade", length=11)
	private int quantidade;
	
	@Column(name = "valor_hora", length=15)
	private Double valorHora;
	
	@ManyToOne
	@JoinColumn(name="empresa")
	private Empresa empresa;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "teto_mensal", length=5)
	private int tetoMensal;
	 
	
}
