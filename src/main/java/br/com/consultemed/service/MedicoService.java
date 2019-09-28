package br.com.consultemed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.consultemed.model.Medico;
import br.com.consultemed.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;
	
	public List<Medico> listar(){
		return medicoRepository.findAll();
	}
	
	@Transactional
	public Medico salvar(Medico medico) {
		Medico medicoSalvo = medicoRepository.save(medico);
		return medicoSalvo;
	}
	
	public List<Medico> filtrar(String nome){
		return medicoRepository.findByNomeCadastrado(nome);
	}

	public Medico getById(Long id) {
		return medicoRepository.findOne(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		medicoRepository.delete(id);
	}
	
	@Transactional
	public Medico editar(Long id, Medico medico) {
		Medico medicoBuscado = medicoRepository.findOne(id);
		medico.setId(medicoBuscado.getId());
		return medicoRepository.save(medico);
	}
}
