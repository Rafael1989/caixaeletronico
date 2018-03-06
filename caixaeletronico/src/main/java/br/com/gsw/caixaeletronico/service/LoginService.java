package br.com.gsw.caixaeletronico.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsw.caixaeletronico.model.Cliente;
import br.com.gsw.caixaeletronico.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;
	
	public Cliente existeCliente(Cliente cliente) {
		return repository.existeCliente(cliente);
	}
	
}
