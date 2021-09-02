package com.gestao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.projeto.exception.BusinessException;
import com.gestao.projeto.model.Contrato;
import com.gestao.projeto.model.Empresa;
import com.gestao.projeto.negocio.ContratoBO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/contrato")
public class ContratoController {
	
	@Autowired
	private ContratoBO contratoBO;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<List<Contrato>> listar() throws BusinessException {		
		try {
			return new ResponseEntity<>(contratoBO.listar(), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@ApiOperation(value = "cadastrar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Contrato> cadastrar(@RequestBody Contrato contrato) throws BusinessException {
		try {
			return new ResponseEntity<>(contratoBO.salvar(contrato), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/apagar", method = RequestMethod.POST)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Contrato> apagar(@RequestBody Contrato contrato) throws BusinessException {
		try {
			return new ResponseEntity<>(contratoBO.apagar(contrato), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
