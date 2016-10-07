package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.web.converter.DatabaseDtoConverter;
import ua.kiev.unicyb.tcct.web.dto.DatabaseDto;

/**
 * @Author Denys Storozhenko.
 */
@RestController
@RequestMapping(value = "/databases")
public class DatabaseController {
	@Autowired
	private DatabaseService databaseService;

	private DatabaseDtoConverter databaseDtoConverter = new DatabaseDtoConverter();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Iterable<DatabaseDto>> getAllDatabases() {
		Iterable<Database> databases = databaseService.findAll();
		Iterable<DatabaseDto> result = databaseDtoConverter.toDtos(databases);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<DatabaseDto> getDatabase(@RequestParam String databaseName) {
		Database database = databaseService.findByName(databaseName);
		DatabaseDto result = databaseDtoConverter.toDto(database);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createDatabase(@RequestBody DatabaseDto databaseDto) {
		Database database = databaseDtoConverter.toEntity(databaseDto);
		databaseService.create(database);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseEntity toFile(@RequestParam String fileName) {
		databaseService.toFile(fileName);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public ResponseEntity fromFile(@RequestParam String fileName) {
		databaseService.loadFromFile(fileName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
