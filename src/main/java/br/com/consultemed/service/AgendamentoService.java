package br.com.consultemed.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consultemed.model.Consulta;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.model.StatusConsulta;
import br.com.consultemed.repository.AgendamentoRepository;
import br.com.consultemed.request.AgendamentoRequest;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private MailService mailSercice;
	
	public Consulta save (AgendamentoRequest consulta) {

		LocalDateTime dataConsulta = LocalDateTime.parse(consulta.getDataAgendamento(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		boolean dataInvalida = validarDataAgendamento(dataConsulta);
		if(dataInvalida) {
			throw new RuntimeErrorException(null, "Data de agendamento anterior a data atual");
		}
		
		Date dataAgendamentoConsulta = converterLocalDateTimeEmDate(dataConsulta);
		
		Paciente paciente = pacienteService.findById(consulta.getIdPaciente());
		Medico medico = medicoService.getById(consulta.getIdMedico());
		
		Consulta consultaPersist = new Consulta();
		consultaPersist.setMedico(medico);
		consultaPersist.setPaciente(paciente);
		consultaPersist.setDataAgendamento(dataAgendamentoConsulta);
		consultaPersist.setStatusConsulta(StatusConsulta.AGENDADO);
		
		Consulta consultaResponse = agendamentoRepository.save(consultaPersist);
		
//		String consultaFormatadaEmail = dataConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
//		dispararEmail(consultaResponse.getPaciente().getEmail(), consultaFormatadaEmail);
		return consultaResponse;
	}

	public List<Consulta> list() {
		List<Consulta> consultas = agendamentoRepository.findAll();
		return consultas;
	}
	
	private boolean validarDataAgendamento(LocalDateTime dataConsulta) {
		Date in = new Date();
		LocalDateTime now = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		return false;
	}
	
	private Date converterLocalDateTimeEmDate(LocalDateTime dataAgendamento) {
		Date in = new Date();
		dataAgendamento = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(dataAgendamento.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}
}
