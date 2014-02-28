package com.daghan.todo.controller.rest;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.exception.BindingIsNotSuccesfulException;
import com.daghan.todo.exception.ResourceDoesNotExistException;
import com.daghan.todo.service.ILoginDetailService;
import com.daghan.todo.service.ITodoService;

@Controller
@RequestMapping(value = "/secure/todos")
// http://localhost:8080/todo/rest/secure/
public class TodoListController extends AbstractController<Todo> {
	@Inject
	ITodoService todoDAO;

	@Inject
	ILoginDetailService loginDetailDAO;

	// serves to http://localhost:8080/todo/rest/secure/todos
	@Override
	public @ResponseBody
	List<Todo> getEntitiesForUser() {
		return todoDAO.getNotesForUser(loginDetailDAO.getAuthenticatedUser()
				.getUserData().getUserDataId());
	}

	// serves to http://localhost:8080/todo/rest/secure/todos/1.json
	@Override
	public @ResponseBody
	Todo getEntityForUser(@PathVariable("ID") Long id)
			throws ResourceDoesNotExistException {
		Todo returnVal = todoDAO.findOne(id);
		if (returnVal == null) {
			throw new ResourceDoesNotExistException("Resource with id " + id
					+ " does not exist.");
		}
		return returnVal;
	}

	// serves to http://localhost:8080/todo/rest/secure/todos.json
	@Override
	public @ResponseBody
	Todo addEntity(@Valid @RequestBody Todo todo, BindingResult bindingResult)
			throws BindingIsNotSuccesfulException {
		checkBindingResult(bindingResult);
		todo.setDateEntered(Calendar.getInstance().getTime());
		todo.setUserDataForeignKey(loginDetailDAO.getAuthenticatedUser()
				.getUserData().getUserDataId());
		todoDAO.create(todo);
		return todo;
	}

	// serves to http://localhost:8080/todo/rest/secure/todos/1.json
	@Override
	public @ResponseBody
	Todo updateEntity(@PathVariable("ID") Long id,
			@Valid @RequestBody Todo todo, BindingResult bindingResult)
			throws BindingIsNotSuccesfulException {
		checkBindingResult(bindingResult);
		Todo existingTodo = todoDAO.findOne(id);
		// partial update
		existingTodo.setDetail(todo.getDetail());
		existingTodo.setPriority(todo.getPriority());
		existingTodo.setDueDate(todo.getDueDate());
		todoDAO.update(existingTodo);

		return existingTodo;
	}

	// serves to http://localhost:8080/todo/rest/secure/todos/1
	@Override
	public @ResponseBody
	String deleteEntity(@PathVariable("ID") Long id) {
		todoDAO.delete(todoDAO.findOne(id));
		return id.toString();
	}

}
