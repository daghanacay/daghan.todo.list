package com.daghan.controller.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Calendar;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.daghan.todo.domain.model.LoginDetail;
import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.domain.model.UserData;
import com.daghan.todo.service.ILoginDetailService;
import com.daghan.todo.service.ITodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:appServlet/RestServicesConfiguration.xml" })
@WebAppConfiguration
public class TestTodoController {

	private MockMvc mockMvc;

	@Inject
	ITodoService todoDAOmock;

	@Inject
	ILoginDetailService loginDetailDAOmock;

	@Inject
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		UserData userDataMock = mock(UserData.class);
		when(userDataMock.getUserDataId()).thenReturn(1L);
		LoginDetail loginDetailmock = mock(LoginDetail.class);
		when(loginDetailmock.getUserData()).thenReturn(userDataMock);
		// loginDetailDAOmock = mock(LoginDetailDAO.class);
		when(loginDetailDAOmock.getAuthenticatedUser()).thenReturn(
				loginDetailmock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();

		Todo first = new Todo() {
			{
				setDetail("detail1");
				setDueDate(Calendar.getInstance().getTime());
				setPriority(1L);
			}
		};
		Todo second = new Todo() {
			{
				setDetail("detail2");
				setDueDate(Calendar.getInstance().getTime());
				setPriority(2L);
			}
		};
		// todoDAOmock = mock(TodoDAO.class);
		when(todoDAOmock.getNotesForUser(1L)).thenReturn(
				Arrays.asList(first, second));
	}

	@Test
	public void testGetEntitiesForUser() throws Exception {
		mockMvc.perform(
				get("/secure/todos.json").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].detail", is("detail1")))
				.andExpect(jsonPath("$[0].priority", is(1)))
				.andExpect(jsonPath("$[1].detail", is("detail2")))
				.andExpect(jsonPath("$[1].priority", is(2)));

		verify(loginDetailDAOmock, times(1)).getAuthenticatedUser();
		verifyNoMoreInteractions(loginDetailDAOmock);

		verify(todoDAOmock, times(1)).getNotesForUser(1L);
		verifyNoMoreInteractions(todoDAOmock);

	}
}
