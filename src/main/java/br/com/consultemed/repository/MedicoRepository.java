package br.com.consultemed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultemed.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

	public List<Medico> findByNomeCadastrado(String nome);
}
