package br.com.consultemed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.consultemed.service.ContatoService;
import br.com.consultemed.service.MedicoService;
import br.com.consultemed.service.PacienteService;
import br.com.consultemed.service.UsuarioService;

@Controller
@RequestMapping
public class HomeController {

	@Autowired
	MedicoService medicoService;
	
	@Autowired
	ContatoService contatoService;
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("pages/home");
		Integer qtdMedicos;
		Integer qtdContatos;
		Integer qtdPacientes;
		Integer qtdUsuarios;
		try {
			qtdMedicos = medicoService.listar().size();
			qtdContatos = contatoService.list().size();
			qtdPacientes = pacienteService.listar().size();
			qtdUsuarios = usuarioService.listar().size();
		} catch(Exception e) {
			e.getStackTrace();
			qtdContatos = 0;
			qtdMedicos = 0;
			qtdPacientes = 0;
			qtdUsuarios = 0;
			mv = adicionarObjetos(mv, qtdMedicos, qtdContatos, qtdPacientes,qtdUsuarios);
			return mv;
		}
		mv = adicionarObjetos(mv, qtdMedicos, qtdContatos, qtdPacientes, qtdUsuarios);
		return mv;
	}

	private ModelAndView adicionarObjetos(ModelAndView mv, Integer qtdMedicos, Integer qtdContatos, Integer qtdPacientes, Integer qtdUsuarios) {
		mv.addObject("qtdMedicos", qtdMedicos);
		mv.addObject("qtdContatos", qtdContatos);
		mv.addObject("qtdPacientes", qtdPacientes);
		mv.addObject("qtdUsuarios", qtdUsuarios);
		return mv;
	}
}
