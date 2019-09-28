package br.com.consultemed.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.consultemed.model.Usuario;

@Resource
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	public static final String LOCATION = "Location";
	
	@GetMapping
	public ResponseEntity<List<Usuario>> list(){
		List<Usuario> usuarios = usuarioService.listar();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> get (@PathVariable("id") Long id){
		Usuario usuarioBuscado = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuarioBuscado);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		Usuario usuarioSalvo = usuarioService.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
		response.setHeader(LOCATION, uri.toASCIIString());
		return ResponseEntity.ok().body(usuarioSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> edit (@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario){
		Usuario usuarioEditado = usuarioService.edit(id, usuario);
		return ResponseEntity.ok().body(usuarioEditado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove (@PathVariable("id") Long id){
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
