package com.daghan.controller.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.daghan.todo.dao.TodoDAO;
import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.service.ITodoService;

public class TestTodoService {

	private static String CORRECT_QUERY = "SELECT * FROM todo_items as todos where todos.fk_USER_DATA_id = ?";
	@Mock
	private EntityManager repositoryMock;

	@InjectMocks
	private ITodoService service = new TodoDAO();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Query mockQuery = mock(Query.class);
		when(mockQuery.setParameter(1, 1L)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mock(List.class));
		when(repositoryMock.createNativeQuery(CORRECT_QUERY, Todo.class))
				.thenReturn(mockQuery);
	}

	@Test
	public void testGetNotesForUser() {
		service.getNotesForUser(1L);
		verify(repositoryMock, times(1)).createNativeQuery(CORRECT_QUERY,
				Todo.class);
		verifyNoMoreInteractions(repositoryMock);
	}
}
