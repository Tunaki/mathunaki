package fr.mathunaki.webapp.controller.tuition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.service.tuition.TuitionService;

@Controller
@RequestMapping("/tuition")
public class TuitionReadController {

	@Autowired
	private TuitionService tuitionService;

	@RequestMapping(value = "/{tuitionId}")
	public String getTuition(@PathVariable("tuitionId") Long tuitionId, Model model) {
		model.addAttribute("tuition", tuitionService.getTuition(tuitionId));
		return "tuition/tuition";
	}

	@RequestMapping(value = "/{tuitionId}", method = RequestMethod.POST, params = "backToList")
	public String backToList() {
		return "redirect:/tuition/list";
	}

	@RequestMapping(value = "/{tuitionId}", method = RequestMethod.POST, params = "delete")
	public String delete(@PathVariable("tuitionId") Long tuitionId, Model model) {
		try {
			tuitionService.deleteTuition(tuitionId);
			return "redirect:/tuition/list";
		} catch (DeleteEntityException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("tuition", tuitionService.getTuition(tuitionId));
			return "tuition/tuition";
		}
	}

}
