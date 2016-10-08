package ua.kiev.unicyb.tcct.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

	@RequestMapping(value = "/{dbName}/tables/{tableName}/columns/{columnName}/records/{recordId}",
			method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity uploadPicture(@PathVariable String dbName, @PathVariable String tableName,
			@PathVariable String columnName, @PathVariable String recordId, @RequestParam("file") MultipartFile file) {
		try {
			recordService.uploadImage(dbName, tableName, columnName, recordId, file.getBytes());
		} catch (IOException e) {
			throw new MultipartException("Multipart file exception.");
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
