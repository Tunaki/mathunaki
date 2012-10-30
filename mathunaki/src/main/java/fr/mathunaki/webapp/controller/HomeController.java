package fr.mathunaki.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/index")
	public ModelAndView helloWorld() {
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("index", "message", message);
	}

}