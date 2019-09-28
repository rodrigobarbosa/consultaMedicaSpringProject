package br.com.consultemed.resource;

import java.util.List;

import javax.annotation.Resource;
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

import br.com.consultemed.model.Medico;

@Resource
@RestController
@RequestMapping("/api/medicos")
public class MedicoResource {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping()
	public ResponseEntity<List<Medico>> list(){
		List<Medico> medicos = medicoService.listar();
		return ResponseEntity.ok().body(medicos);
	}
	
	@PostMapping()
	public ResponseEntity<Medico> add(@Valid @RequestBody Medico medico){
		Medico medicoSalvo = medicoService.salvar(medico);
		return ResponseEntity.ok().body(medicoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Medico> remove(@PathVariable("id") Long id){
		Medico medicoDeletado = medicoService.getById(id);
		medicoService.deleteById(id);
		return ResponseEntity.ok().body(medicoDeletado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medico> update(@PathVariable("id") Long id, @RequestBody Medico medico){
		Medico medicoEditado = medicoService.editar(id, medico);
		return ResponseEntity.ok().body(medicoEditado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico> find (@PathVariable("id") Long id){
		Medico medicoBuscado = medicoService.getById(id);
		return ResponseEntity.ok().body(medicoBuscado);
	}
}
