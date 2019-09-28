package br.com.consultemed.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Login não pode ser vazio.")
	private String login;
	
	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;
	
	@NotBlank(message = "Senha não pode ser vazia.")
	private String senha;
	
	@NotBlank(message = "E-mail não pode ser vazio.")
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Permissao roles;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
}
