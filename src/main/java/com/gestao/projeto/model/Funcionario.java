package com.gestao.projeto.model;

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
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Geração do ID de Funcionario")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cargo")
	private String cargo;
	
	@ManyToOne
	@JoinColumn(name="empresa")
	private Empresa empresa;

}
