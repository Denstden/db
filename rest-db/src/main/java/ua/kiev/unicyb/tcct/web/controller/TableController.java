package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.web.converter.TableDtoConverter;
import ua.kiev.unicyb.tcct.web.dto.TableDto;

/**
 * @Author Denys Storozhenko.
 */
@RestController
@RequestMapping(value = "/databases")
public class TableController {
	@Autowired
	private TableService tableService;

	private TableDtoConverter tableDtoConverter = new TableDtoConverter();

	@RequestMapping(value = "/{dbName}/tables", method = RequestMethod.POST)
	public ResponseEntity createTable(@PathVariable String dbName, @RequestBody TableDto tableDto) {
		Table table = tableDtoConverter.toEntity(tableDto);
		tableService.addTable(dbName, table);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
