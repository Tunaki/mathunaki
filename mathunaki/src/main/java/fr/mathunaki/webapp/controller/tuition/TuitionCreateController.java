package fr.mathunaki.webapp.controller.tuition;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.mathunaki.database.entity.Tuition;
import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.exception.EntityNotFoundException;
import fr.mathunaki.database.exception.FileTooLargeException;
import fr.mathunaki.database.service.tuition.TuitionService;
import fr.mathunaki.database.service.user.UserService;

@Controller
@RequestMapping("/tuition")
public class TuitionCreateController {

	@Autowired
	private TuitionService tuitionService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String prepareCreate(Model model) {
		model.addAttribute("tuition", tuitionService.newTuition());
		return "tuition/tuitionCreate";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "create")
	public String create(@ModelAttribute("tuition") @Valid Tuition tuition, BindingResult result,
			RedirectAttributes redirectAttrs, @RequestParam("file") MultipartFile file,
			@RequestParam("userId") String userIdStr) {
		Long userId = null;
		byte[] bytes = null;
		try {
			userId = Long.parseLong(userIdStr);
		} catch (NumberFormatException e) {
			result.rejectValue("user", "fr.mathunaki.validation.user.mandatory");
		}
		if (!file.isEmpty()) {
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				result.rejectValue("resource", "fr.mathunaki.validation.file.invalid");
			}
		}
		if (result.hasErrors()) {
			return "tuition/tuitionCreate";
		} else {
			tuition.setResource(bytes);
			try {
				tuitionService.saveTuition(tuition, userId);
			} catch (EntityNotFoundException e) {
				result.rejectValue("user", e.getMessage());
				return "tuition/tuitionCreate";
			} catch (FileTooLargeException e) {
				result.rejectValue("resource", e.getMessage());
				return "tuition/tuitionCreate";
			}
			redirectAttrs.addAttribute("tuitionId", tuition.getId());
			return "redirect:/tuition/{tuitionId}";
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "cancel")
	public String cancel() {
		return "redirect:/tuition/list";
	}

	@RequestMapping(value = "/userSearchDialog", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getMatchingUsers(@RequestParam String name) {
		return userService.getUserListWithName(name);
	}

}
