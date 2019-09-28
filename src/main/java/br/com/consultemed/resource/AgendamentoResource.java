package br.com.consultemed.resource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.consultemed.model.Consulta;
import br.com.consultemed.request.AgendamentoRequest;

@Resource
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoResource {

	@Autowired
	AgendamentoService agendamentoService;
	
	@PostMapping
	public ResponseEntity<Consulta> agendar(@RequestBody AgendamentoRequest request) {
		Consulta agendamento = agendamentoService.save(request);
		return ResponseEntity.ok().body(agendamento);
	}

	@GetMapping
	public ResponseEntity<?> listarConsultas(){
		List<Consulta> consultas = agendamentoService.list();
		return ResponseEntity.ok().body(consultas);
	}
}
