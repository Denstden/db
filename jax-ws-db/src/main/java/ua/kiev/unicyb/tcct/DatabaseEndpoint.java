package ua.kiev.unicyb.tcct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.xml.DatabaseDtoRequest;
import ua.kiev.unicyb.tcct.xml.TableDtoRequest;

/**
 * @Author Denys Storozhenko.
 */
@Endpoint
public class DatabaseEndpoint {
	private static final String NAMESPACE_URI = "ua.kiev.unicyb.tcct";

	@Autowired
	private DatabaseService databaseService;

	@Autowired
	private TableService tableService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "databaseDtoRequest")
	@ResponsePayload
	public Database getDatabase(@RequestPayload DatabaseDtoRequest request) {
		return databaseService.findByName(request.getDatabaseName());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "tableDtoRequest")
	@ResponsePayload
	public Table getTable(@RequestPayload TableDtoRequest request) {
		return tableService.findTableByName(request.getDatabaseName(), request.getTableName());
	}
}
