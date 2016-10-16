package ua.kiev.unicyb.tcct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.column.ColumnService;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.service.table.IntersectionService;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.xml.DatabaseDtoRequest;
import ua.kiev.unicyb.tcct.xml.GetColumnByNameDtoRequest;
import ua.kiev.unicyb.tcct.xml.GetTableIntersectionDtoRequest;
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
	private IntersectionService intersectionService;

	@Autowired
	private TableService tableService;

	@Autowired
	private ColumnService columnService;

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

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getColumnByNameDtoRequest")
	@ResponsePayload
	public Column getColumn(@RequestPayload GetColumnByNameDtoRequest request) {
		return columnService
				.getColumnByName(request.getDatabaseName(), request.getTableName(), request.getColumnName());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTableIntersectionDtoRequest")
	@ResponsePayload
	public Table getTableIntersection(@RequestPayload GetTableIntersectionDtoRequest request) {
		return intersectionService.intersect(request.getDb1Name(), request.getDb2Name(), request.getTable1Name(),
				request.getTable2Name());
	}
}
