package com.daghan.todo.service;

import org.springframework.stereotype.Service;

import com.daghan.todo.domain.model.LoginDetail;
@Service
public interface ILoginDetailService extends IAbstractService<LoginDetail> {
	public abstract LoginDetail getAuthenticatedUser();
}