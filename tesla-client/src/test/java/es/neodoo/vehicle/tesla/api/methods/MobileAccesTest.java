package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.MobileAcces;
import es.neodoo.vehicle.tesla.api.params.MobileAccesResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class MobileAccesTest {

	@Test
	public void testParseRespose() {

		try {

			String jsonResponse = "{\"response\":true}";
			MobileAccesResponse mar = MobileAccesResponse
					.toObject(jsonResponse);
			String jsonFromObject = mar.toJson();
			assertEquals(jsonResponse, jsonFromObject);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testMobileAcces() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			MobileAcces mobileAcces = new MobileAcces(teslaInvoker);
			MobileAccesResponse mobileAccesResponse = mobileAcces.execute(1);
			assertNotNull(mobileAccesResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}