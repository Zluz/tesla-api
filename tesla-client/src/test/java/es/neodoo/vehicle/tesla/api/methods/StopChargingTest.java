package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.StopCharging;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.api.params.StopChargingResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class StopChargingTest {

	@Test
	public void testParseResponse() {

		try {

			StopChargingResponse stopChargingResponse = new StopChargingResponse(
					new ResponseParamVehicleCommands(true, ""));
			String stringStopChargingResponse = stopChargingResponse.toJson();
			StopChargingResponse stopChargingResponseConverted = StopChargingResponse
					.toObject(stringStopChargingResponse);
			assertEquals(stringStopChargingResponse,
					stopChargingResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testStopCharging() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {
			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			StopCharging stopCharging = new StopCharging(teslaInvoker);
			StopChargingResponse stopChargingResponse = stopCharging.execute(1);
			assertNotNull(stopChargingResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}