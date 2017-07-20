package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.WakeUpCar;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.api.params.WakeUpCarResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class WakeUpCarTest {

	@Test
	public void testParseResponse() {

		try {

			WakeUpCarResponse wakeUpCarResponse = new WakeUpCarResponse(
					new ResponseParamVehicleCommands(true, ""));
			String stringWakeUpCarResponse = wakeUpCarResponse.toJson();
			WakeUpCarResponse wakeUpCarResponseConverted = WakeUpCarResponse
					.toObject(stringWakeUpCarResponse);
			assertEquals(stringWakeUpCarResponse,
					wakeUpCarResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testWakeUpCar() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			WakeUpCar wakeUpCar = new WakeUpCar(teslaInvoker);
			WakeUpCarResponse wakeUpCarResponse = wakeUpCar.execute(1);
			assertNotNull(wakeUpCarResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}