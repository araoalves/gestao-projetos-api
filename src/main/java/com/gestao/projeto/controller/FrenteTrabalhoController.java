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

import com.gestao.projeto.Projection.FrenteTrabalhoResumoProjection;
import com.gestao.projeto.exception.BusinessException;
import com.gestao.projeto.filter.FrentetrabalhoFilter;
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
	public ResponseEntity<Page<FrenteTrabalho>> listar(FrentetrabalhoFilter filtro,Pageable pageble) throws BusinessException {	
	
		try {
			return new ResponseEntity<>(frentetrabalhoBO.listar(filtro,pageble), HttpStatus.OK);
			
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
	public ResponseEntity<Page<FrenteTrabalho>> listarPorContrato(@PathVariable("id") Long id, Pageable pageble) throws BusinessException {		
		try {
			return new ResponseEntity<>(frentetrabalhoBO.listarPorEmpresa(id,pageble), HttpStatus.OK);
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/relatoriogeral/{emp}/{cont}/{status}", method = RequestMethod.GET)
	@ApiOperation(value = "relatoriogeral", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<?> relatorioGeral(@PathVariable("emp")Long emp, @PathVariable("cont") Long cont, @PathVariable("status") String status) throws BusinessException {		
		try {	
			return new ResponseEntity<>(frentetrabalhoBO.gerarGeral(emp,cont, status), HttpStatus.OK);
					
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/relatorioresumo/{emp}", method = RequestMethod.GET)
	@ApiOperation(value = "relatorioresumo", authorizations = { @Authorization(value="apiKey") })
	public ResponseEntity<FrenteTrabalhoResumoProjection> relatorioResumo(@PathVariable("emp")Long emp) throws BusinessException {		
		try {	
			return new ResponseEntity<>(frentetrabalhoBO.gerarResumo(emp), HttpStatus.OK);
					
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Editar dados da Frente de Trabalho", authorizations = { @Authorization(value="apiKey") })
	@PutMapping("/editar/{id}")
	public ResponseEntity<FrenteTrabalho> editar(@PathVariable("id") long id, @RequestBody FrenteTrabalho frenteTrabalho) throws BusinessException {				
		try {
			
			Optional<FrenteTrabalho> frenteTrabalhoAtual = frentetrabalhoBO.findById(id);

			if (frenteTrabalhoAtual.isPresent()) {
				FrenteTrabalho _frenteTrabalho = frenteTrabalhoAtual.get();
				_frenteTrabalho.setDataInicio(frenteTrabalho.getDataInicio());
				_frenteTrabalho.setDataFim(frenteTrabalho.getDataFim());
				_frenteTrabalho.setDescricaoAtividade(frenteTrabalho.getDescricaoAtividade());
				_frenteTrabalho.setQuantidadeHoras(frenteTrabalho.getQuantidadeHoras());
				_frenteTrabalho.setFuncionario(frenteTrabalho.getFuncionario());
				_frenteTrabalho.setContrato(frenteTrabalho.getContrato());
				_frenteTrabalho.setEmpresa(frenteTrabalho.getEmpresa());
				return new ResponseEntity<>(frentetrabalhoBO.salvar(_frenteTrabalho), HttpStatus.OK);
			} else {
				throw new BusinessException("Atividade n??o encontrada.");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}		
	}

}
