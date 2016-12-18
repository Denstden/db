package ua.kiev.unicyb.tcct.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class DatabaseResource {
	@Autowired
	private DatabaseService databaseService;

	@RequestMapping(value = "createDatabaseView", method = {RequestMethod.GET})
	public String getCreateDatabaseView() {
		return "createDatabase";
	}

	@RequestMapping(value = "createDatabase", method = {RequestMethod.POST})
	public String createDatabase(@RequestParam(name = "dbName") String dbName) {
		Database database = new Database();
		database.setDatabaseName(dbName);
		databaseService.create(database);
		return "createDatabase";
	}

	@RequestMapping(value = "allDatabases", method = {RequestMethod.GET})
	public String findAll(Model model) {
		model.addAttribute("databases", databaseService.findAll());
		return "databases";
	}
}
