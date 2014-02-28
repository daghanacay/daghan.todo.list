package com.daghan.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daghan.todo.domain.model.Todo;
@Service
public interface ITodoService extends IAbstractService<Todo>{

	public abstract List<Todo> getNotesForUser(Long userId);

}