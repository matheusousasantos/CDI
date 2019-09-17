package br.com.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;
import br.com.jppautil.JPAUtil;

public class IDaoLancamentoImpl implements IDaoLancamento {

	@Override
	public List<Lancamento> consultar(Long id) {
		
		List< Lancamento > lancamentos = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		lancamentos = entityManager.createQuery("from Lancamento where usuario.id = '" + id + "'").getResultList();
		transaction.commit();
		entityManager.close();
		
		return lancamentos;
	}
	
	

}
