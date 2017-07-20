package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.UnlockDoors;
import es.neodoo.vehicle.tesla.api.params.ResponseParamVehicleCommands;
import es.neodoo.vehicle.tesla.api.params.UnlockDoorsResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class UnlockDoorsTest {

	@Test
	public void testParseResponse() {

		try {

			UnlockDoorsResponse unlockDoorsResponse = new UnlockDoorsResponse(
					new ResponseParamVehicleCommands(true, ""));
			String stringUnlockDoorsResponse = unlockDoorsResponse.toJson();
			UnlockDoorsResponse unlockDoorsResponseConverted = UnlockDoorsResponse
					.toObject(stringUnlockDoorsResponse);
			assertEquals(stringUnlockDoorsResponse,
					unlockDoorsResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testUnlockDoors() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			UnlockDoors unlockDoors = new UnlockDoors(teslaInvoker);
			UnlockDoorsResponse unlockDoorsResponse = unlockDoors.execute(1);
			assertNotNull(unlockDoorsResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}