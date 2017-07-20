package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import org.junit.Test;

import es.neodoo.vehicle.tesla.api.methods.ListVehicles;
import es.neodoo.vehicle.tesla.api.params.ListVehiclesResponse;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class ListVehiclesTest {

	@Test
	public void testParseResponse() {

		try {

			String jsonResponse = "{\"response\":[{\"color\":null,\"display_name\":null,\"id\":321,\"options_codes\":\"MS01,RENA,TM00,DRLH,PF00,BT85,PBCW,RFPO,WT19,IBMB,IDPB,TR00,SU01,SC01,TP01,AU01,CH00,HP00,PA00,PS00,AD02,X020,X025,X001,X003,X007,X011,X013\",\"user_id\":123,\"vehicle_id\":1234567890,\"vin\":\"5YJSA1CN5CFP01657\",\"tokens\":[\"x\",\"x\"],\"state\":\"online\"}],\"count\":1}";
			ListVehiclesResponse listVehiclesResponse = new ListVehiclesResponse();
			listVehiclesResponse = listVehiclesResponse.toObject(jsonResponse);
			String jsonFromObject = listVehiclesResponse.toJson();
			assertEquals(jsonResponse, jsonFromObject);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

	// @Test
	public void testListVehicle() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			ListVehicles listVehicles = new ListVehicles(teslaInvoker);
			ListVehiclesResponse listVehiclesResponse = listVehicles.execute();
			assertNotNull(listVehiclesResponse);

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}