package br.com.consultemed.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import br.com.consultemed.model.Contato;
import lombok.extern.slf4j.Slf4j;

@Resource
@RestController
@RequestMapping("/api/contatos")
@Slf4j
public class ContatoResource {

	@Autowired
	private ContatoService contatoService;
	
	public static final String LOCATION = "Location";
	
	@GetMapping
	public ResponseEntity<List<Contato>> list(){
		return ResponseEntity.ok().body(contatoService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> get (@PathVariable("id") Long id){
		Contato contatoBuscado = contatoService.getById(id);
		return ResponseEntity.ok().body(contatoBuscado);
	}
	
	@PostMapping
	public ResponseEntity<Contato> save(@Valid @RequestBody Contato contato, HttpServletRequest request, HttpServletResponse response){
		Contato contatoSalvo = contatoService.save(contato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(contatoSalvo.getId()).toUri();
		log.info("Salvando contato");
		response.setHeader(LOCATION, uri.toASCIIString());
		return ResponseEntity.ok().body(contatoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> edit (@PathVariable("id") Long id, @Valid @RequestBody Contato contato){
		Contato contatoEditado = contatoService.edit(id, contato);
		return ResponseEntity.ok().body(contatoEditado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove (@PathVariable("id") Long id){
		contatoService.remove(id);
		return ResponseEntity.noContent().build();
	}
}
