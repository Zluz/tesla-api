package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.ResetValetPin;
import es.neodoo.vehicle.tesla.api.params.ResetValetPinResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class ResetValetPinTest {

	// @Test
	public void testResetValetPin() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {
			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			ResetValetPin resetValetPin = new ResetValetPin(teslaInvoker);
			ResetValetPinResponse resetValetPinResponse = resetValetPin
					.execute(1);
			assertNotNull(resetValetPinResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}