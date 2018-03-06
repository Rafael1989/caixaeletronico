package br.com.gsw.caixaeletronico;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gsw.caixaeletronico.model.Cliente;
import br.com.gsw.caixaeletronico.service.LoginService;


@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Cliente cliente, HttpSession session) {
		Cliente existeCliente = service.existeCliente(cliente);
		if(existeCliente != null) {
			session.setAttribute("clienteLogado", existeCliente);
			return "redirect:/";
		}
		return "redirect:login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
