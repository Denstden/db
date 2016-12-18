package ua.kiev.unicyb.tcct.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class HelloResource {
	@Autowired
	private DatabaseService databaseService;



	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String hello(Model model) {
		fillDbNames(model);
		return "index";
	}

	private void fillDbNames(Model model) {
		List<String> dbNames = new ArrayList<>();
		databaseService.findAll().forEach(database -> dbNames.add(database.getDatabaseName()));
		model.addAttribute("dbNames", dbNames);
	}
}
