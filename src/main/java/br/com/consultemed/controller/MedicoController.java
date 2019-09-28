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

import br.com.consultemed.model.Medico;
import br.com.consultemed.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	MedicoService medicoService;
	
	private static final String PAGES_MEDICO_MEDICOS = "pages/medico/medicos";
	private static final String PAGES_MEDICO_NOVO = "pages/medico/novo_medico";
	
	@GetMapping
	public ModelAndView list(@ModelAttribute("filtro") Medico medico) {
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_MEDICOS);
		if(!StringUtils.isEmpty(medico.getNome())) {
			List<Medico> medicos = this.medicoService.filtrar(medico.getNome());
			mv.addObject("medicos", medicos);
		}else {
			mv.addObject("medicos", medicoService.listar());
		}
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Medico medico, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/medicos");
		
		if (result.hasErrors()) {
			return novo(medico);
		}

		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso");
		this.medicoService.salvar(medico);
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Medico medico) {
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_NOVO);
		mv.addObject("medico", medico);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Medico medico = this.medicoService.getById(id);  
		return novo(medico);
	}
}
