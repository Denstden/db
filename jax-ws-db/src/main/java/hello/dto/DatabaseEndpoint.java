package hello.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Endpoint
public class DatabaseEndpoint {
	private static final String NAMESPACE_URI = "ua.kiev.unicyb.tcct";

	@Autowired
	private DatabaseService databaseService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "databaseDtoRequest")
	@ResponsePayload
	public Database getCountry(@RequestPayload DatabaseDtoRequest request) {
		//		Database response = new Database();
		//		response.setCountry(countryRepository.findCountry(request.getName()));

		return databaseService.findByName(request.getDatabaseName());
	}
}
