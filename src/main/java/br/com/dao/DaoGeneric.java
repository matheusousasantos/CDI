package br.com.dao;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jppautil.JPAUtil;

@Named
public class DaoGeneric<T> {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;
	
	public void salvar(T entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entidade);
		entityTransaction.commit();
		
	}
	
	public T merge(T entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		T retorno = entityManager.merge(entidade);
		entityTransaction.commit();		
		return retorno;
		
	}
	
	public void delete(T entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entidade);
		entityTransaction.commit();
		
	}
	
	public void deletePorId(T entidade) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		entityTransaction.commit();
		
	}
	
	
	public List<T> getListEntity(Class<T> entidade) {
		
		EntityManager entityManager = jpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<T> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		entityTransaction.commit();
		return retorno;
		
	}
	
	
	
}
