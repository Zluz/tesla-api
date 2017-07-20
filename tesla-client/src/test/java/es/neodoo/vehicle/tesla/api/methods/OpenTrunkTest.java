package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.OpenTrunk;
import es.neodoo.vehicle.tesla.api.params.OpenTrunkResponse;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class OpenTrunkTest {
	@Test
	public void testParseResponse() {

		try {

			OpenTrunkResponse openTrunkResponse = new OpenTrunkResponse(
					new ResponseParamVehicleCommands(true, ""));
			String stringOpenTrunkResponse = openTrunkResponse.toJson();
			OpenTrunkResponse openTrunkResponseConverted = OpenTrunkResponse
					.toObject(stringOpenTrunkResponse);
			assertEquals(stringOpenTrunkResponse,
					openTrunkResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testRemoteStart() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {
			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			OpenTrunk openTrunk = new OpenTrunk(teslaInvoker);
			OpenTrunkResponse openTrunkResponse = openTrunk.execute(1, "trunk");
			assertNotNull(openTrunkResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}
