package br.com.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;
import br.com.jppautil.JPAUtil;

@Named
public class IDaoLancamentoImpl implements IDaoLancamento {
	
	@Inject
	private EntityManager entityManager;
	

	@Override
	public List<Lancamento> consultar(Long id) {
		
		List< Lancamento > lancamentos = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		lancamentos = entityManager.createQuery("from Lancamento where usuario.id = '" + id + "'").getResultList();
		transaction.commit();		
		return lancamentos;
	}
	
	

}
