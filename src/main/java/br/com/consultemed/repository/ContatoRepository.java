package br.com.consultemed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultemed.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	public List<Contato> findByNomeCadastrado(String nome);
}
