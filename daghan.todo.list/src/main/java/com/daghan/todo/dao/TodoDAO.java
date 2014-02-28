package com.daghan.todo.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.service.ITodoService;

@Repository
public class TodoDAO extends AbstractJpaDAO<Todo> implements ITodoService {
	@Inject
	@PersistenceContext
	EntityManager entityManager;

	public TodoDAO() {
		setClazz(Todo.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daghan.todo.dao.ITodoService#getNotesForUser(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Todo> getNotesForUser(Long userId) {
		String sqlQuery = "SELECT * FROM todo_items as todos where todos.fk_USER_DATA_id = ?";
		return (List<Todo>) entityManager
				.createNativeQuery(sqlQuery, Todo.class)
				.setParameter(1, userId).getResultList();

	}
}
