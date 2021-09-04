package com.gestao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.projeto.Projection.FrenteTrabalhoGeralProjection;
import com.gestao.projeto.Projection.FrenteTrabalhoResumoProjection;
import com.gestao.projeto.exception.BusinessException;
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
	
	
	@RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<HttpStatus> apagar(@PathVariable("id") Long id) throws BusinessException {
		try {
			frentetrabalhoBO.apagar(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "empresa", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<List<FrenteTrabalho>> listarPorContrato(@PathVariable("id") Long id) throws BusinessException {		
		try {
			return new ResponseEntity<>(frentetrabalhoBO.listarPorEmpresa(id), HttpStatus.OK);
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/relatoriogeral/{emp}/{cont}", method = RequestMethod.GET)
	@ApiOperation(value = "relatoriogeral", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<List<FrenteTrabalhoGeralProjection>> relatorioGeral(@PathVariable("emp")Long emp,@PathVariable("cont") Long cont) throws BusinessException {		
		try {	
			return new ResponseEntity<>(frentetrabalhoBO.gerarGeral(emp,cont), HttpStatus.OK);
					
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/relatorioresumo/{emp}", method = RequestMethod.GET)
	@ApiOperation(value = "relatorioresumo", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<List<FrenteTrabalhoResumoProjection>> relatorioResumo(@PathVariable("emp")Long emp) throws BusinessException {		
		try {	
			return new ResponseEntity<>(frentetrabalhoBO.gerarResumo(emp), HttpStatus.OK);
					
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
