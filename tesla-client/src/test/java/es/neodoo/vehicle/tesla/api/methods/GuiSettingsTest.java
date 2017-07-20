package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.GuiSettings;
import es.neodoo.vehicle.tesla.api.params.GuiSettingsParamResponse;
import es.neodoo.vehicle.tesla.api.params.GuiSettingsResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class GuiSettingsTest {

	@Test
	public void testParseResponse() {

		try {

			GuiSettingsResponse guiSettingsResponse = new GuiSettingsResponse(
					new GuiSettingsParamResponse("mi/hr", "F", "mi/hr", false,
							"Rated"));
			String stringGuiSettingsResponse = guiSettingsResponse.toJson();
			GuiSettingsResponse guiSettingsResponseConverted = GuiSettingsResponse
					.toObject(stringGuiSettingsResponse);
			Assert.assertEquals(stringGuiSettingsResponse,
					guiSettingsResponseConverted.toJson());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testGuiSettings() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			GuiSettings guiSettings = new GuiSettings(teslaInvoker);
			GuiSettingsResponse guiSettingsResponse = guiSettings.execute(1);
			assertNotNull(guiSettingsResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}