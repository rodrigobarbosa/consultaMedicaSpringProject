package br.com.consultemed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.consultemed.model.Contato;
import br.com.consultemed.model.dto.ContatoDTO;
import br.com.consultemed.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;
	
	@Transactional(readOnly=true)
	public List<Contato> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public Contato save(Contato contato) {
		return repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Contato getById(Long id) {
		return this.repository.findOne(id);
	}
	
	@Transactional
	public Contato edit(Long id, Contato contato) {
		Contato contatoBuscado = getById(id);
		contato.setId(contatoBuscado.getId());
		return save(contato);
	}
	
	public List<Contato> filtrar(ContatoDTO contato) {
		String nome = contato.getNome() == null ? "%" : contato.getNome()+"%";
		return repository.findByNomeCadastrado(nome);
	}


	@Transactional
	public boolean ativarDesativar(Long id) {
		
		
		boolean ativou = false;
		
		Contato contato = this.repository.getOne(id);
		if(contato.isAtivo()) {
			contato.setAtivo(false);
			return ativou;
		}else {
			contato.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarContato(Contato contato) {
		if (contato.isAtivo()) {
			ativaDesativaUsuario(contato);
		} else {
			ativaDesativaUsuario(contato);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Contato contato) {

		if (contato.isAtivo()) {
			contato.setAtivo(false);
		} else {
			contato.setAtivo(true);
		}

		this.repository.saveAndFlush(contato);
	}
}
