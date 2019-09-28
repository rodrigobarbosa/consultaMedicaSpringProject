package br.com.consultemed.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean verificaLogin(String email, String senha) {
		boolean logou = false;
		
		if(email.equals("rodrigo.barba.88@gmail.com") && senha.equals("admin")) {
			logou = true;
		}
		return logou;
	}
}
