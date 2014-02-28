package com.daghan.todo.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorController {

	@RequestMapping(value = "/errors/404")
	public String handle404() {
		return "/errors/404ErrorPage";
	}

	@RequestMapping(value = "/errors/403")
	public String handle403() {
		return "/errors/403ErrorPage";
	}
}