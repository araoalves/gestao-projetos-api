package com.gestao.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "empresa")
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Geração do ID de clientes")
    private long id;
	
	@Column(name = "descricao", length=150)
	private String descricao;
	
	@Column(name = "cnpj", length=20)
	private String cnpj;

}
