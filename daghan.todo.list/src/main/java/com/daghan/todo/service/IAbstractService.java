package com.daghan.todo.service;

import java.util.List;

public interface IAbstractService<T> {

	public abstract void setClazz(Class<T> clazzToSet);

	public abstract T findOne(Object identityVal);

	public abstract List<T> findAll();

	public abstract void create(T entity);

	public abstract T update(T entity);

	/**
	 * returns the first entity that matches the value in a given column
	 * 
	 * @param columnName
	 *            column name that exist in the table corresponding to this BO
	 * @param value
	 *            row value for this column
	 * @return returns the first matching value if any or null if none or column
	 *         does not exist
	 */
	public abstract T getEntityByColumnName(String columnName, String value);

	public abstract void delete(T entity);

	public abstract void deleteById(long entityId);

}