package com.daghan.todo.controller.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.exception.BindingIsNotSuccesfulException;
import com.daghan.todo.exception.ResourceDoesNotExistException;
@Controller
public abstract class AbstractController<T> {
	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception anExc, HttpServletResponse response) {
		System.out.println("Exception handled by " + this.getClass());
		response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		try {
			response.getWriter().write("<br/>");
			response.getWriter().write(anExc.getMessage());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void checkBindingResult(BindingResult bindingResult)
			throws BindingIsNotSuccesfulException {
		if (bindingResult.hasErrors()) {
			BindingIsNotSuccesfulException exception = new BindingIsNotSuccesfulException();
			for (FieldError error : bindingResult.getFieldErrors()) {
				exception.addError(error.getField() + " "
						+ error.getDefaultMessage());
			}
			throw exception;
		}
	}

	// GET returns the a list of entities T for the current user
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	abstract public @ResponseBody
	List<T> getEntitiesForUser();

	// GET returns a single entity T for the current user
	abstract @RequestMapping(value = "/{ID}", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody
	T getEntityForUser(@PathVariable("ID") Long id)
			throws ResourceDoesNotExistException;

	// POST creates an entity T for the current user and returns the object
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	abstract public @ResponseBody
	T addEntity(@Valid @RequestBody Todo todo, BindingResult bindingResult)
			throws BindingIsNotSuccesfulException;

	// PUT updates an entity for the current user returns the updated entity
	@RequestMapping(value = "/{ID}", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" })
	abstract public @ResponseBody
	T updateEntity(@PathVariable("ID") Long id, @Valid @RequestBody T entity,
			BindingResult bindingResult) throws BindingIsNotSuccesfulException;

	// DELETE deletes the entity of the current user with the given ID returns
	// the id of the deleted entity
	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
	abstract public @ResponseBody
	String deleteEntity(@PathVariable("ID") Long id);
}
