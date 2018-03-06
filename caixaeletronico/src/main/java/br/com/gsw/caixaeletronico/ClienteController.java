package br.com.gsw.caixaeletronico;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gsw.caixaeletronico.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping("/")
	public String index() {
		return "caixaeletronico";
	}
	
	@RequestMapping("saca")
	public ModelAndView saca(BigDecimal valor,HttpSession session) {
		return service.validaESacaValor(valor,session);
	}
	
}
