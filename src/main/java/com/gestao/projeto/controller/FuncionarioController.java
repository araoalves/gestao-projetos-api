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
import com.gestao.projeto.filter.FuncionarioFilter;
import com.gestao.projeto.model.Funcionario;
import com.gestao.projeto.negocio.FuncionarioBO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioBO funcionarioBO;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Page<Funcionario>> listar(FuncionarioFilter filter, Pageable pageble) throws BusinessException {		
		try {
			return new ResponseEntity<>(funcionarioBO.listar(filter, pageble), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@ApiOperation(value = "cadastrar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionario) throws BusinessException {
		try {
			return new ResponseEntity<>(funcionarioBO.salvar(funcionario), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "apagar", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<HttpStatus> apagar(@PathVariable("id") Long id) throws BusinessException {
		try {
			funcionarioBO.apagar(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Editar dados do funcionário", authorizations = { @Authorization(value="apiKey") })
	@PutMapping("/editar/{id}")
	public ResponseEntity<Funcionario> editar(@PathVariable("id") long id, @RequestBody Funcionario funcionario) throws BusinessException {				
		try {
			
			Optional<Funcionario> funcionarioAtual = funcionarioBO.findById(id);

			if (funcionarioAtual.isPresent()) {
				Funcionario _funcionario = funcionarioAtual.get();
				_funcionario.setNome(funcionario.getNome());
				_funcionario.setCargo(funcionario.getCargo());
				return new ResponseEntity<>(funcionarioBO.salvar(_funcionario), HttpStatus.OK);
			} else {
				throw new BusinessException("Funcionário não encontrada.");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}		
	}

}
