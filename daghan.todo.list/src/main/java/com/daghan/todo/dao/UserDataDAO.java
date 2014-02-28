package com.daghan.todo.dao;

import org.springframework.stereotype.Repository;

import com.daghan.todo.domain.model.UserData;
import com.daghan.todo.service.IUserDataService;

@Repository
public class UserDataDAO extends AbstractJpaDAO<UserData> implements IUserDataService {
	public UserDataDAO() {
		setClazz(UserData.class);
	}

}
