package com.daghan.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.daghan.todo.service.IAbstractService;

public abstract class AbstractJpaDAO<T> implements IAbstractService<T> {

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#setClazz(java.lang.Class)
	 */
	@Override
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#findOne(java.lang.Object)
	 */
	@Override
	public T findOne(Object identityVal) {
		return entityManager.find(clazz, identityVal);
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#create(T)
	 */
	@Override
	@Transactional
	public void create(T entity) {
		entityManager.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#update(T)
	 */
	@Override
	@Transactional
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#getEntityByColumnName(java.lang.String, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T getEntityByColumnName(String columnName, String value) {
		return (T) entityManager.createQuery(
				"from " + clazz.getName() + " where " + columnName + "='"
						+ value + "'").getSingleResult();
	}
	
	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#delete(T)
	 */
	@Override
	@Transactional
	public void delete(T entity) {
		T attached = entityManager.merge(entity);
		entityManager.remove(attached);
	}

	/* (non-Javadoc)
	 * @see com.daghan.todo.dao.IAbstratcDAOI#deleteById(long)
	 */
	@Override
	@Transactional
	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
