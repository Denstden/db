package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.IntersectionService;
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
	@Autowired
	private IntersectionService intersectionService;

	private TableDtoConverter tableDtoConverter = new TableDtoConverter();

	@RequestMapping(value = "/{dbName}/tables", method = RequestMethod.POST)
	public ResponseEntity createTable(@PathVariable String dbName, @RequestBody TableDto tableDto) {
		Table table = tableDtoConverter.toEntity(tableDto);
		tableService.addTable(dbName, table);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/tables/intersection", method = RequestMethod.GET, produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<TableDto> getIntersection(@RequestParam String dbName1, @RequestParam String dbName2,
			@RequestParam String tableName1, @RequestParam String tableName2) {
		Table table = intersectionService.intersect(dbName1, dbName2, tableName1, tableName2);
		TableDto tableDto = tableDtoConverter.toDto(table);
		return new ResponseEntity<>(tableDto, HttpStatus.OK);
	}
}
