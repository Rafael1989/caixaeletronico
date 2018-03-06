package br.com.gsw.caixaeletronico.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.gsw.caixaeletronico.model.Cliente;
import br.com.gsw.caixaeletronico.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public void salvar(Cliente cliente) {
		repository.save(cliente);
	}
	
	public ModelAndView validaESacaValor(BigDecimal valor, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("caixaeletronico");
		Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
		BigDecimal cem = new BigDecimal(100);
		BigDecimal cinquenta = new BigDecimal(50);
		BigDecimal vinte = new BigDecimal(20);
		BigDecimal dez = new BigDecimal(10);
		BigDecimal notasCem = new BigDecimal(0);
		BigDecimal notasCinquenta = new BigDecimal(0);
		BigDecimal notasVinte = new BigDecimal(0);
		BigDecimal notasDez = new BigDecimal(0);
		if(valor != null) {
			BigDecimal valorSacado = valor;
			boolean valorValido = true;
			
			if(valor.compareTo(cliente.getSaldo())<=0) {
				if(valor.compareTo(cem)>=0) {
					notasCem = valor.divide(cem);
					valor = valor.remainder(cem);
				}
				if(valor.compareTo(cinquenta)>=0) {
					notasCinquenta = valor.divide(cinquenta);
					valor = valor.remainder(cinquenta);
				}
				if(valor.compareTo(vinte)>=0) {
					notasVinte = valor.divide(vinte);
					valor = valor.remainder(vinte);
				}
				if(valor.compareTo(dez)>=0) {
					notasDez = valor.divide(dez);
					valor = valor.remainder(dez);
				}
				if(valor.compareTo(new BigDecimal(0))>0) {
					modelAndView.addObject("mensagem", "Só temos notas de 100, 50, 20 e 10");
					valorValido = false;
				}
			}else {
				modelAndView.addObject("mensagem", "Saldo insuficiente");
				valorValido = false;
			}
			if(valorValido) {
				cliente.setSaldo(cliente.getSaldo().subtract(valorSacado));
				salvar(cliente);
			}
		}else {
			modelAndView.addObject("mensagem", "Favor informar o valor que deseja sacar");
		}
		
		modelAndView.addObject("notasCem", notasCem.setScale(0,RoundingMode.DOWN));
		modelAndView.addObject("notasCinquenta", notasCinquenta.setScale(0,RoundingMode.DOWN));
		modelAndView.addObject("notasVinte", notasVinte.setScale(0,RoundingMode.DOWN));
		modelAndView.addObject("notasDez", notasDez.setScale(0,RoundingMode.DOWN));
		return modelAndView;
	}
	

}
