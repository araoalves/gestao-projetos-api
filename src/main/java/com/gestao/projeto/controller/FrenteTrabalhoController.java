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
import com.gestao.projeto.model.Empresa;
import com.gestao.projeto.model.FrenteTrabalho;
import com.gestao.projeto.negocio.FrenteTrabalhoBO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/frentetrabalho")
public class FrenteTrabalhoController {
	
	@Autowired
	private FrenteTrabalhoBO frentetrabalhoBO;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<List<FrenteTrabalho>> listar() throws BusinessException {		
		try {
			return new ResponseEntity<>(frentetrabalhoBO.listar(), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@ApiOperation(value = "cadastrar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<FrenteTrabalho> cadastrar(@RequestBody FrenteTrabalho frentetrabalho) throws BusinessException {
		try {
			return new ResponseEntity<>(frentetrabalhoBO.salvar(frentetrabalho), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/apagar", method = RequestMethod.POST)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<FrenteTrabalho> apagar(@RequestBody FrenteTrabalho frentetrabalho) throws BusinessException {
		try {
			return new ResponseEntity<>(frentetrabalhoBO.apagar(frentetrabalho), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
