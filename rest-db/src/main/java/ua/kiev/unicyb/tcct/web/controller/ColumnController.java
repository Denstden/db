package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.service.column.ColumnService;
import ua.kiev.unicyb.tcct.web.converter.ColumnDtoConverter;
import ua.kiev.unicyb.tcct.web.dto.ColumnDto;

/**
 * @Author Denys Storozhenko.
 */
@RestController
@RequestMapping(value = "/databases")
public class ColumnController {
	@Autowired
	private ColumnService columnService;

	private ColumnDtoConverter columnDtoConverter = new ColumnDtoConverter();

	@RequestMapping(value = "/{dbName}/tables/{tableName}/columns", method = RequestMethod.POST)
	public ResponseEntity createColumn(@PathVariable String dbName, @PathVariable String tableName,
			@RequestBody ColumnDto columnDto) {
		Column column = columnDtoConverter.toEntity(columnDto);
		columnService.addColumn(dbName, tableName, column);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{dbName}/tables/{tableName}/columns", method = RequestMethod.PUT)
	public ResponseEntity updateColumn(@PathVariable String dbName, @PathVariable String tableName,
			@RequestBody ColumnDto columnDto) {
		Column column = columnDtoConverter.toEntity(columnDto);
		columnService.updateColumn(dbName, tableName, column);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
