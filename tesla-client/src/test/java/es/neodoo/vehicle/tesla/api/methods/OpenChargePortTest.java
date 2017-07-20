package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.OpenChargePort;
import es.neodoo.vehicle.tesla.api.params.OpenChargePortResponse;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class OpenChargePortTest {

	@Test
	public void testParseResponse() {
		try {
			OpenChargePortResponse openChargePortResponse = new OpenChargePortResponse(
					new ResponseParamVehicleCommands(true, ""));
			String stringOpenChargePortResponse = openChargePortResponse
					.toJson();
			OpenChargePortResponse openChargePortResponseConverted = OpenChargePortResponse
					.toObject(stringOpenChargePortResponse);
			assertEquals(stringOpenChargePortResponse,
					openChargePortResponseConverted.toJson());
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	// @Test
	public void testOpenChargePort() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			OpenChargePort openChargePort = new OpenChargePort(teslaInvoker);
			OpenChargePortResponse openChargePortResponse = openChargePort
					.execute(1);
			assertNotNull(openChargePortResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}
