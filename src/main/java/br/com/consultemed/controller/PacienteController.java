package br.com.consultemed.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.model.dto.ContatoDTO;
import br.com.consultemed.service.PacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService service;

	private static final String PAGES_PACIENTE_PACIENTES = "pages/paciente/pacientes";
	private static final String PAGES_PACIENTE_PACIENTES_NOVO = "pages/paciente/novo_paciente";

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") ContatoDTO filtro) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_PACIENTES);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Paciente> pacientes = this.service.findByNomeCadastrado(filtro.getNome());
			mv.addObject("pacientes", pacientes);
			
		}else {
			mv.addObject("pacientes", service.listar());
		}
		
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Paciente paciente) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_PACIENTES_NOVO);
		mv.addObject("paciente", paciente);
		return mv;

	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		
		if (result.hasErrors()) {
			return novo(paciente);
		}

		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
		this.service.save(paciente);
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Paciente paciente = this.service.findById(id);
		return novo(paciente);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deletar(@PathVariable("id") Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		Paciente p = service.findById(id);
		attributes.addFlashAttribute("removido", String.format("Paciente {%s} removido com sucesso", p.getNome()));
		service.remove(id);
		return mv;
	}
}
