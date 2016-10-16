package ua.kiev.unicyb.tcct;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.xml.DatabaseDtoRequest;
import ua.kiev.unicyb.tcct.xml.GetTableIntersectionDtoRequest;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class WSClient extends WebServiceGatewaySupport {
	public Database getDatabaseByName(String dbName) {
		DatabaseDtoRequest request = new DatabaseDtoRequest();
		request.setDatabaseName(dbName);

		return (Database) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", request);
	}

	public Table intersect(String db1Name, String db2Name, String table1Name, String table2Name) {
		GetTableIntersectionDtoRequest request = new GetTableIntersectionDtoRequest();
		request.setDb1Name(db1Name);
		request.setDb2Name(db2Name);
		request.setTable1Name(table1Name);
		request.setTable2Name(table2Name);

		return (Table) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", request);
	}
}
