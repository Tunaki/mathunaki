package fr.mathunaki.webapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserReadController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userId}")
	public String getUser(@PathVariable("userId") Long userId, Model model) {
		model.addAttribute("user", userService.getUser(userId));
		return "user/user";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, params = "backToList")
	public String backToList() {
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, params = "disable")
	public String disable(@PathVariable("userId") Long userId, RedirectAttributes redirectAttrs) {
		userService.disableUser(userId);
		redirectAttrs.addAttribute("userId", userId);
		return "redirect:/user/{userId}";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, params = "enable")
	public String enable(@PathVariable("userId") Long userId, RedirectAttributes redirectAttrs) {
		userService.enableUser(userId);
		redirectAttrs.addAttribute("userId", userId);
		return "redirect:/user/{userId}";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, params = "delete")
	public String delete(@PathVariable("userId") Long userId, Model model) {
		try {
			userService.deleteUser(userId);
			return "redirect:/user/list";
		} catch (DeleteEntityException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", userService.getUser(userId));
			return "user/user";
		}
	}

}
