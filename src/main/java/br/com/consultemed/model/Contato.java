package br.com.consultemed.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@Data
@Entity
@Table(name="contatos")
public class Contato {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	
	@Getter
	@Setter
	@NotEmpty(message="Email é obrigatório")
	private String email;
	
	private boolean ativo = true;
}
