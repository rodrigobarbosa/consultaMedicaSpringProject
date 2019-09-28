package br.com.consultemed.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consultemed.model.Usuario;
import br.com.consultemed.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) {
		System.out.println("Id do Usuario: " + usuario.getId());
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findOne(id);
	}
	
	public void deleteById(Long id) {
		usuarioRepository.delete(id);
	}
	
	public void editById(Long id) {
		Usuario u = usuarioRepository.findOne(id);
		usuarioRepository.saveAndFlush(u);
	}

	public Usuario edit(Long id, Usuario usuario) {
		Usuario userSearched = usuarioRepository.findOne(id);
		usuario.setId(userSearched.getId());
		return usuarioRepository.saveAndFlush(usuario);
	}
}
