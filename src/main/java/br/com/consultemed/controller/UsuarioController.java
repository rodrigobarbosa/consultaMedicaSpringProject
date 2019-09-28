package br.com.consultemed.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.consultemed.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	public static final String PAGE_USUARIO_USUARIO = "pages/usuario/usuarios";
	public static final String PAGE_USUARIO_NOVO = "pages/usuario/novo_usuario";

	@Autowired
	UsuarioService usuarioService;
	@GetMapping
	public ModelAndView list(Usuario usuario) {
		ModelAndView mv = new ModelAndView(PAGE_USUARIO_USUARIO);
		mv.addObject("usuarios", usuarioService.listar());
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView(PAGE_USUARIO_NOVO);
		mv.addObject("usuario", usuario);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");

		if (result.hasErrors()) {
			return novo(usuario);
		}

		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso");
		this.usuarioService.save(usuario);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.findById(id);
		return novo(usuario);
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		usuarioService.deleteById(id);
		return mv;
	}
}
