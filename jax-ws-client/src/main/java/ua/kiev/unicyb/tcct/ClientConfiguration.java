package ua.kiev.unicyb.tcct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @Author Denys Storozhenko.
 */
@Configuration
public class ClientConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("ua.kiev.unicyb.tcct");
		return marshaller;
	}

	@Bean
	public WSClient wsClient(Jaxb2Marshaller marshaller) {
		WSClient wsClient = new WSClient();
		wsClient.setDefaultUri("http://localhost:8080/ws");
		wsClient.setMarshaller(marshaller);
		wsClient.setUnmarshaller(marshaller);
		return wsClient;
	}
}
