package fr.mathunaki.webapp.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * ControllerSetup allows the definition of methods global to every controller
 * class. It works as if every controller inherited this class.
 */
@ControllerAdvice
public class ControllerSetup {

	/**
	 * Init the web data binder.
	 * 
	 * @param binder Web data binder.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		// binder.registerCustomEditor(Boolean.class, new
		// CustomBooleanEditor(true));
	}

	// /**
	// * Handle exception of type {@link EntityNotFoundException}
	// *
	// * @param e EntityNotFoundException.
	// * @return Model and view object.
	// */
	// @ExceptionHandler(EntityNotFoundException.class)
	// public ModelAndView handleEntityNotFoundException(EntityNotFoundException
	// e) {
	// ModelAndView mav = new ModelAndView("exception/entityNotFound");
	// mav.addObject("exception", e);
	// return mav;
	// }

}