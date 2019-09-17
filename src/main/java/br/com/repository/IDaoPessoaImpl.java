package br.com.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Pessoa;
import br.com.jppautil.JPAUtil;

public class IDaoPessoaImpl implements IDaoPessoa {
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		
		Pessoa pessoa = null;
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		pessoa = (Pessoa) entityManager.createQuery("Select p from Pessoa p where "
				+ " p.login = '" + login + "' and p.senha = '" + senha + "'").getSingleResult();
		
		entityTransaction.commit();		
		return pessoa;
	}

}
