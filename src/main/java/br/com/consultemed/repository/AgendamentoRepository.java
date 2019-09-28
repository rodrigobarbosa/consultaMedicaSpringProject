package br.com.consultemed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultemed.model.Consulta;

@Repository
public interface AgendamentoRepository extends JpaRepository<Consulta, Long> {

}
