package com.gestao.projeto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.projeto.exception.BusinessException;
import com.gestao.projeto.model.Contrato;
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
	public ResponseEntity<Page<Contrato>> listar(Pageable pageble) throws BusinessException {		
		try {
			return new ResponseEntity<>(contratoBO.listar(pageble), HttpStatus.OK);
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
	
	
	@RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<HttpStatus> apagar(@PathVariable("id") Long id) throws BusinessException {
		try {
			contratoBO.apagar(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Editar Contrato", authorizations = { @Authorization(value="apiKey") })
	@PutMapping("/editar/{id}")
	public ResponseEntity<Contrato> editar(@PathVariable("id") long id, @RequestBody Contrato contrato) throws BusinessException {				
		try {
			
			Optional<Contrato> contratoAtual = contratoBO.findById(id);

			if (contratoAtual.isPresent()) {
				Contrato _contrato = contratoAtual.get();
				_contrato.setDescricao(contrato.getDescricao());
				_contrato.setData(contrato.getData());
				_contrato.setQuantidade(contrato.getQuantidade());
				_contrato.setTetoMensal(contrato.getTetoMensal());
				_contrato.setValorHora(contrato.getValorHora());
				return new ResponseEntity<>(contratoBO.salvar(_contrato), HttpStatus.OK);
			} else {
				throw new BusinessException("Empresa não encontrada.");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}		
	}

}
