package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.VehicleState;
import es.neodoo.vehicle.tesla.api.params.VehicleStateParamResponse;
import es.neodoo.vehicle.tesla.api.params.VehicleStateResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class VehicleStateTest {

	@Test
	public void testParseResponse() {

		try {

			VehicleStateResponse vehicleStateResponse = new VehicleStateResponse(
					new VehicleStateParamResponse(false, false, false, false,
							false, false, "1.19.42", true, false, "unknow", 0,
							false, "Base19", false, "Colored", "Base"));
			String stringvehicleStateResponse = vehicleStateResponse.toJson();
			VehicleStateResponse vehicleStateResponseConverted = VehicleStateResponse
					.toObject(stringvehicleStateResponse);
			Assert.assertEquals(stringvehicleStateResponse,
					vehicleStateResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testVehicleState() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			VehicleState vehicleState = new VehicleState(teslaInvoker);
			VehicleStateResponse vehicleStateResponse = vehicleState.execute(1);
			assertNotNull(vehicleStateResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}