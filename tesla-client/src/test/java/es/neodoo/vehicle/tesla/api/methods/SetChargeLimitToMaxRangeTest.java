package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.SetChargeLimitToMaxRange;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.api.params.SetChargeLimitToMaxRangeResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class SetChargeLimitToMaxRangeTest {

	@Test
	public void testParseResponse() {

		try {

			SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponse = new SetChargeLimitToMaxRangeResponse(
					new ResponseParamVehicleCommands(false,
							"already_max_range"));
			String stringSetChargeLimitToMaxRangeResponse = setChargeLimitToMaxRangeResponse
					.toJson();
			SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponseConverted = SetChargeLimitToMaxRangeResponse
					.toObject(stringSetChargeLimitToMaxRangeResponse);
			assertEquals(stringSetChargeLimitToMaxRangeResponse,
					setChargeLimitToMaxRangeResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testSetChargeLimitToMaxRange() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			SetChargeLimitToMaxRange setChargeLimitToMaxRange = new SetChargeLimitToMaxRange(
					teslaInvoker);
			SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponse = setChargeLimitToMaxRange
					.execute(1);
			assertNotNull(setChargeLimitToMaxRangeResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}