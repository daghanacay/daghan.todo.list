package com.daghan.todo.exception;

import java.util.ArrayList;
import java.util.List;

public class BindingIsNotSuccesfulException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> errorList = new ArrayList<String>();

	@Override
	public String getMessage() {
		StringBuffer returnVal = new StringBuffer();
		for (String error : errorList) {
			returnVal.append(error).append("<br/>");
		}
		return returnVal.toString();
	}

	public void addError(String errorStr) {
		errorList.add(errorStr);
	}

}
