package fr.mathunaki.webapp.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.service.user.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserUpdateController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userId}/update")
	public String prepareUpdate(@PathVariable("userId") Long userId, Model model) {
		model.addAttribute("user", userService.getUser(userId));
		return "user/userUpdate";
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST, params = "cancel")
	public String cancelUpdate(@PathVariable("userId") Long userId, RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("userId", userId);
		return "redirect:/user/{userId}";
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST, params = "update")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult result,
			RedirectAttributes redirectAttrs, SessionStatus status) {
		if (result.hasErrors()) {
			return "user/userUpdate";
		} else {
			userService.saveUser(user);
			redirectAttrs.addAttribute("userId", user.getId());
			status.setComplete();
			return "redirect:/user/{userId}";
		}
	}

}
