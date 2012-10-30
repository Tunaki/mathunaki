package fr.mathunaki.webapp.controller.tuition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.mathunaki.database.service.tuition.TuitionService;

@Controller
@RequestMapping("/tuition")
public class TuitionListController {

	@Autowired
	private TuitionService tuitionService;

	@RequestMapping(value = "/list")
	public String getUserList(Model model) {
		model.addAttribute("tuitionList", tuitionService.getTuitionList());
		return "tuition/tuitionList";
	}

}
