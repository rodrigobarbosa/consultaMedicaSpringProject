package br.com.consultemed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultemed.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	public List<Paciente> findByNomeCadastrado(String nome);
}
