package com.gestao.projeto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Double quantidade;
	
	@Column(name = "valor_hora", length=15)
	private Double valorHora;
	
	@ManyToOne
	@JoinColumn(name="empresa")
	private Empresa empresa;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR", timezone = "America/Sao_Paulo")
	private Date data;
	
	@Column(name = "teto_mensal", length=5)
	private Double tetoMensal;
	 
	
}
