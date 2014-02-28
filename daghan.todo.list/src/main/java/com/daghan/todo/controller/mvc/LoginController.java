package com.daghan.todo.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	// Serves to localhost/todo/mvc/login
	// shows the initial login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String checkLogin(ModelMap model) {
		return "login";
	}

	// Serves to localhost/todo/mvc/loginfailed
	// shows the login failed page
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error",
				"Your login and password combination is wrong.");
		return "login";

	}

	// Serves to localhost/todo/mvc/logout
	// shows the logout page
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}

	// Serves to localhost/todo/mvc/secure/home
	// shows the logout page
	@RequestMapping(value = "/secure/home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		return "secure/home";
	}
}
