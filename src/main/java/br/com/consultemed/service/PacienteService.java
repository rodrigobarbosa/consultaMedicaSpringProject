package br.com.consultemed.service;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;
	
	public List<Paciente> listar(){
		return pacienteRepository.findAll();
	}
	
	public Paciente findById(Long id) {
		return pacienteRepository.findOne(id);
	}
	
	public List<Paciente> findByNomeCadastrado(String nome){
		return pacienteRepository.findByNomeCadastrado(nome);
	}
	
	public Paciente save(Paciente paciente) {
		return pacienteRepository.save(paciente);		
	}

	public Paciente edit(Long id, Paciente paciente) {
		Paciente pacienteBuscado = findById(id);
		paciente.setId(pacienteBuscado.getId());
		return pacienteRepository.save(paciente);		
	}

	public void remove(Long id) {
		pacienteRepository.delete(id);
	}
}
