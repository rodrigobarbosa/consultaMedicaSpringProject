package br.com.consultemed.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message = "Nome é obrigatório.")
	private String nome;
	
	@NotEmpty(message = "CPF é obrigatório.")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@NotEmpty(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotEmpty(message = "E-mail é obrigatório!")
	@Email(message = "E-mail não é váido")
	private String email;
	
	@OneToMany
	private List<Consulta> consultas;
}
