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
import com.gestao.projeto.filter.EmpresaFilter;
import com.gestao.projeto.model.Empresa;
import com.gestao.projeto.negocio.EmpresaBO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaBO empresaBO;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Page<Empresa>> listar(EmpresaFilter filter, Pageable pageble) throws BusinessException {		
		try {
			return new ResponseEntity<>(empresaBO.listar(filter, pageble), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@ApiOperation(value = "cadastrar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa) throws BusinessException {
		try {
			return new ResponseEntity<>(empresaBO.salvar(empresa), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<HttpStatus> apagar(@PathVariable("id") Long id) throws BusinessException {
		try {
			empresaBO.apagar(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Editar Empresa", authorizations = { @Authorization(value="apiKey") })
	@PutMapping("/editar/{id}")
	public ResponseEntity<Empresa> editar(@PathVariable("id") long id, @RequestBody Empresa empresa) throws BusinessException {				
		try {
			
			Optional<Empresa> empresaAtual = empresaBO.findById(id);

			if (empresaAtual.isPresent()) {
				Empresa _empresa = empresaAtual.get();
				_empresa.setDescricao(empresa.getDescricao());
				_empresa.setCnpj(empresa.getCnpj());
				return new ResponseEntity<>(empresaBO.salvar(_empresa), HttpStatus.OK);
			} else {
				throw new BusinessException("Empresa não encontrada.");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}		
	}
	
	
}
