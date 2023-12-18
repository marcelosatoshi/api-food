package com.marcelo.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.exception.EntidadeEmUsoException;
import com.marcelo.food.domain.exception.GrupoNaoEncontradoException;
import com.marcelo.food.domain.model.Grupo;
import com.marcelo.food.domain.model.Permissao;
import com.marcelo.food.domain.model.Usuario;
import com.marcelo.food.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);

	}

	@Transactional
	public void deletar(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}
	}

	public Grupo buscarOuFalhar(Long id) {
		return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	@Transactional
	public void disassociarPermissao(Long grupoId, Long PermissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(PermissaoId);
		
		grupo.getPermissoes().remove(permissao);

	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long PermissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(PermissaoId);
		
		grupo.adicionarPermissoes(permissao);
	}


	
	

}
