package fr.mathunaki.webapp.controller.tuition;

import java.io.IOException;

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
			RedirectAttributes redirectAttrs, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			addFileErrorOrReturnBytes(file, result);
			return "tuition/tuitionCreate";
		} else {
			if (!file.isEmpty()) {
				byte[] bytes = addFileErrorOrReturnBytes(file, result);
				if (bytes == null) {
					return "tuition/tuitionCreate";
				}
				tuition.setResource(bytes);
			}
			// tuitionService.saveTuition(tuition);
			redirectAttrs.addAttribute("tuitionId", tuition.getId());
			// return "redirect:/tuition/{tuitionId}";
			return "tuition/tuitionCreate";
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "cancel")
	public String cancel() {
		return "redirect:/tuition/list";
	}

	@RequestMapping(value = "/userSearchDialog", method = RequestMethod.GET)
	@ResponseBody
	public void getMatchingUsers(@RequestParam String name, Model model) {
		model.addAttribute("userSearchList", userService.getUserListWithName(name));
	}

	private byte[] addFileErrorOrReturnBytes(MultipartFile file, BindingResult result) {
		byte[] bytes = null;
		if (!file.isEmpty()) {
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				result.rejectValue("resource", "fr.mathunaki.validation.file.invalid");
			}
		}
		return bytes;
	}

}
