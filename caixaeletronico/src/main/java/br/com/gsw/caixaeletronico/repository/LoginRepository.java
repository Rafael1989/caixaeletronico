package br.com.gsw.caixaeletronico.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gsw.caixaeletronico.model.Cliente;

@Repository
public class LoginRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Cliente existeCliente(Cliente cliente) {
		if(cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}
		
		TypedQuery<Cliente> query = em.createQuery("select c from cliente c where login = :login and senha = :senha",Cliente.class);
		query.setParameter("login", cliente.getLogin());
		query.setParameter("senha", cliente.getSenha());
		try {
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}
}
