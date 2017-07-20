package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.ClimateSettings;
import es.neodoo.vehicle.tesla.api.params.ClimateSettingsParamResponse;
import es.neodoo.vehicle.tesla.api.params.ClimateSettingsResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class ClimateSettingsTest {

	@Test
	public void testParseResponse() {

		try {

			ClimateSettingsResponse climateSettingsResponse = new ClimateSettingsResponse(
					new ClimateSettingsParamResponse(17.0, 9.5, 22.6, 22.6,
							false, null, false, 0));
			String stringClimateSettingsResponse = climateSettingsResponse
					.toJson();
			ClimateSettingsResponse climateSettingsResponseConverted = ClimateSettingsResponse
					.toObject(stringClimateSettingsResponse);
			Assert.assertEquals(stringClimateSettingsResponse,
					climateSettingsResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testClimateSettings() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			ClimateSettings climateSettings = new ClimateSettings(teslaInvoker);
			ClimateSettingsResponse climateSettingsResponse = climateSettings
					.execute(1);
			assertNotNull(climateSettingsResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}