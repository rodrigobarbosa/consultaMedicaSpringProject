package br.com.consultemed.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.consultemed.model.Usuario;
import br.com.consultemed.service.LoginService;
import lombok.Data;

@Controller
@Data
@RequestMapping("/login")
public class LoginController {

	private static final String PAGES_LOGIN = "pages/login";
	
	@Autowired
	private LoginService service;
	
	private Usuario usuario;
	
	@Autowired
	public LoginController(LoginService service) {
		this.service = service;
	}
	
	@GetMapping
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView(PAGES_LOGIN);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@PostMapping
	public ModelAndView login(Usuario usuario, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv;
		boolean resultLogin = service.verificaLogin(usuario.getLogin(), usuario.getSenha());
		String contextPath = request.getRemoteAddr();
		if(resultLogin) {
			request.setAttribute("login", usuario.getLogin());
			mv = new ModelAndView("/pages/home");
		} else {
			mv = new ModelAndView("/login");
		}
		return mv;
	}
}
