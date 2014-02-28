package com.daghan.todo.dao;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.daghan.todo.domain.model.LoginDetail;
import com.daghan.todo.service.ILoginDetailService;

@Repository
public class LoginDetailDAO extends AbstractJpaDAO<LoginDetail> implements ILoginDetailService {

	public LoginDetailDAO() {
		setClazz(LoginDetail.class);
	}

	/**
	 * returns the user table for the authenticated user
	 * 
	 * @return
	 */
	public LoginDetail getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return this.getEntityByColumnName("login", auth.getName());
	}

}
