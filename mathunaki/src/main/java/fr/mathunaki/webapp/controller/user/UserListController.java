package fr.mathunaki.webapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.mathunaki.database.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list")
	public String getUserList(Model model) {
		model.addAttribute("userList", userService.getUserList());
		return "user/userList";
	}

}
