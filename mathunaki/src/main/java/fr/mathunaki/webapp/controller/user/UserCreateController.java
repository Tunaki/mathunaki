package fr.mathunaki.webapp.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserCreateController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String prepareCreate(Model model) {
		model.addAttribute("user", userService.newUser());
		return "user/userCreate";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "create")
	public String create(@ModelAttribute("user") @Valid User user, BindingResult result,
			RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "user/userCreate";
		} else {
			userService.saveUser(user);
			redirectAttrs.addAttribute("userId", user.getId());
			return "redirect:/user/{userId}";
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "cancel")
	public String cancel() {
		return "redirect:/user/list";
	}

}
