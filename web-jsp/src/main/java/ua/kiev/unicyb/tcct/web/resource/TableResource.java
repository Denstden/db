package ua.kiev.unicyb.tcct.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.service.table.IntersectionService;
import ua.kiev.unicyb.tcct.service.table.TableService;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class TableResource {
	@Autowired
	private TableService tableService;
	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private IntersectionService intersectionService;

	@RequestMapping(value = "createTableView", method = {RequestMethod.GET})
	public String getCreateTableView(Model model) {
		List<Database> databases = (List<Database>) databaseService.findAll();
		model.addAttribute("databases", databases);
		return "createTable";
	}

	@RequestMapping(value = "createTable", method = {RequestMethod.POST})
	public String createTable(@RequestParam(name = "tableName") String tableName,
			@RequestParam(name = "dbName") String dbName) {
		Table table = new Table();
		table.setTableName(tableName);
		tableService.addTable(dbName, table);
		return "index";
	}

	@RequestMapping(value = "allTables", method = {RequestMethod.GET})
	public String findAll(@RequestParam(name = "dbName") String dbName, Model model) {
		model.addAttribute("tables", tableService.findAllTables(dbName));
		return "tables";
	}

	@RequestMapping(value = "table", method = {RequestMethod.GET})
	public String getTableContent(@RequestParam(name = "dbName") String dbName,
			@RequestParam(name = "tableName") String tableName, Model model) {
		Table table = tableService.findTableByName(dbName, tableName);
		model.addAttribute("tableView", table);
		model.addAttribute("tableName", table.getTableName());
		return "tableView";
	}

	@RequestMapping(value = "intersection", method = {RequestMethod.GET})
	public String getTableIntersection(@RequestParam(name = "dbName1") String dbName1,
			@RequestParam(name = "tableName1") String tableName1, @RequestParam(name = "dbName2") String dbName2,
			@RequestParam(name = "tableName2") String tableName2, Model model) {
		Table table = intersectionService.intersect(dbName1, dbName2, tableName1, tableName2);
		model.addAttribute("tableView", table);
		model.addAttribute("tableName", table.getTableName());
		return "tableView";
	}
}
