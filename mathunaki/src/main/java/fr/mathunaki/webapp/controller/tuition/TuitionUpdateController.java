package fr.mathunaki.webapp.controller.tuition;

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

import fr.mathunaki.database.entity.Tuition;
import fr.mathunaki.database.service.tuition.TuitionService;

@Controller
@RequestMapping("/tuition")
@SessionAttributes("tuition")
public class TuitionUpdateController {

	@Autowired
	private TuitionService tuitionService;

	@RequestMapping(value = "/{tuitionId}/update")
	public String prepareUpdate(@PathVariable("tuitionId") Long tuitionId, Model model) {
		model.addAttribute("tuition", tuitionService.getTuition(tuitionId));
		return "tuition/tuitionUpdate";
	}

	@RequestMapping(value = "/{tuitionId}/update", method = RequestMethod.POST, params = "cancel")
	public String cancelUpdate(@PathVariable("tuitionId") Long tuitionId,
			RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("tuitionId", tuitionId);
		return "redirect:/tuition/{tuitionId}";
	}

	@RequestMapping(value = "/{tuitionId}/update", method = RequestMethod.POST, params = "update")
	public String update(@ModelAttribute("tuition") @Valid Tuition tuition, BindingResult result,
			RedirectAttributes redirectAttrs, SessionStatus status) {
		if (result.hasErrors()) {
			return "tuition/tuitionUpdate";
		} else {
			tuitionService.saveTuition(tuition);
			redirectAttrs.addAttribute("tuitionId", tuition.getId());
			status.setComplete();
			return "redirect:/tuition/{tuitionId}";
		}
	}

}
