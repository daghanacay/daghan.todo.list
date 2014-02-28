package com.daghan.todo.controller.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daghan.todo.domain.model.LoginDetail;
import com.daghan.todo.domain.model.Registration;
import com.daghan.todo.domain.model.Todo;
import com.daghan.todo.domain.model.UserData;
import com.daghan.todo.domain.model.UserRole;
import com.daghan.todo.exception.BindingIsNotSuccesfulException;
import com.daghan.todo.exception.NotUniqueException;
import com.daghan.todo.service.ILoginDetailService;

@Controller
public class RegisterController extends AbstractController<Registration> {
	@Inject
	ILoginDetailService loginDetailDAO;

	@Inject
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	// Serves to localhost/todo/rest/register
	// registers the user using json data
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody
	String register(@Valid @RequestBody Registration regInfo,
			BindingResult bindingResult, HttpServletRequest request)
			throws BindingIsNotSuccesfulException, NotUniqueException {
		checkBindingResult(bindingResult);
		try {
			LoginDetail loginDetail = new LoginDetail();
			loginDetail.setLogin(regInfo.getEmail());
			loginDetail.setPassword(regInfo.getPassword());
			UserRole userRole = new UserRole();
			userRole.setLoginDetail(loginDetail);
			loginDetail.setUserRole(userRole);
			UserData userData = new UserData();
			userData.setFirstName(regInfo.getFirstName());
			userData.setLastName(regInfo.getLastName());
			loginDetail.setUserData(userData);
			userData.setLoginDetail(loginDetail);
			loginDetailDAO.create(loginDetail);

			authenticateUserAndSetSession(loginDetail, request);
		} catch (PersistenceException exp) {
			throw new NotUniqueException(String.format(
					"User %s exists please use another user name.",
					new Object[] { regInfo.getEmail() }));
		}

		return "success";
	}

	// Automatically authenticate the user after successful registration
	private void authenticateUserAndSetSession(LoginDetail loginDetail,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				loginDetail.getLogin(), loginDetail.getPassword());

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

	}

	@Override
	public Registration getEntityForUser(Long id) {
		// not implemented for this controller
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Registration> getEntitiesForUser() {
		// not implemented for this controller
		throw new UnsupportedOperationException();
	}

	@Override
	public Registration addEntity(Todo todo, BindingResult bindingResult)
			throws BindingIsNotSuccesfulException {
		// not implemented for this controller
		throw new UnsupportedOperationException();
	}

	@Override
	public Registration updateEntity(Long id, Registration entity,
			BindingResult bindingResult) throws BindingIsNotSuccesfulException {
		// not implemented for this controller
		throw new UnsupportedOperationException();
	}

	@Override
	public String deleteEntity(Long id) {
		// not implemented for this controller
		throw new UnsupportedOperationException();
	}
}
