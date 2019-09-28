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

import br.com.consultemed.model.Paciente;

@Resource
@RestController
@RequestMapping("/api/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteService pacienteService;
	
	public static final String LOCATION = "Location";
	
	@GetMapping
	public ResponseEntity<List<Paciente>> list(){
		List<Paciente> pacientes = pacienteService.listar();
		return ResponseEntity.ok().body(pacientes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> get (@PathVariable("id") Long id){
		Paciente pacienteBuscado = pacienteService.findById(id);
		return ResponseEntity.ok().body(pacienteBuscado);
	}
	
	@PostMapping
	public ResponseEntity<Paciente> save(@Valid @RequestBody Paciente paciente, HttpServletResponse response){
		Paciente pacienteSalvo = pacienteService.save(paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
		response.setHeader(LOCATION, uri.toASCIIString());
		return ResponseEntity.ok().body(pacienteSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Paciente> edit (@PathVariable("id") Long id, @Valid @RequestBody Paciente paciente){
		Paciente pacienteEditado = pacienteService.edit(id, paciente);
		return ResponseEntity.ok().body(pacienteEditado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove (@PathVariable("id") Long id){
		pacienteService.remove(id);
		return ResponseEntity.noContent().build();
	}
}
