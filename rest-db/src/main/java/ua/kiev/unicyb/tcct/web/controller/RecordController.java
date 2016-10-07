package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.service.record.RecordService;
import ua.kiev.unicyb.tcct.web.converter.RecordDtoConverter;
import ua.kiev.unicyb.tcct.web.dto.RecordDto;

/**
 * @Author Denys Storozhenko.
 */
@RestController
@RequestMapping(value = "/databases")
public class RecordController {
	@Autowired
	private RecordService recordService;

	@Autowired
	private RecordDtoConverter recordDtoConverter;

	@RequestMapping(value = "/{dbName}/tables/{tableName}/records", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity createRecord(@PathVariable String dbName, @PathVariable String tableName,
			@RequestBody RecordDto recordDto) {
		Record record = recordDtoConverter.toEntity(recordDto);
		recordService.addRecord(dbName, tableName, record);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{dbName}/tables/{tableName}/records", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity updateRecord(@PathVariable String dbName, @PathVariable String tableName,
			@RequestBody RecordDto recordDto) {
		Record record = recordDtoConverter.toEntity(recordDto);
		recordService.updateRecord(dbName, tableName, record);
		return new ResponseEntity(HttpStatus.OK);
	}
}
