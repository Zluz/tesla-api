package es.neodoo.vehicle.tesla.api.methods;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.neodoo.vehicle.tesla.api.params.OauthRequest;
import es.neodoo.vehicle.tesla.api.params.OauthResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;
import junit.framework.Assert;

public class OauthInvokerTest {

	@SuppressWarnings("static-access")
	@Test
	public void testParseResponse()
			throws JsonParseException, JsonMappingException, IOException {
		OauthResponse response = new OauthResponse();
		String respuesta = "{&access_token&:&b269ddb84c056bc00d0a4c362029fb0653924d44b36e6a41f693408aeb3dcdcf&,&token_type&:&bearer&,&expires_in&:3888000,&refresh_token&:&07340e36b8690597d819b5e6ab584860bfbba1dffe160b0342caa1a6676713b7&,&created_at&:&1493809681&}";
		respuesta = respuesta.replaceAll("&", "\"");
		response = response.toObject(respuesta);
		Assert.assertEquals(
				"b269ddb84c056bc00d0a4c362029fb0653924d44b36e6a41f693408aeb3dcdcf",
				response.getAccess_token());
	}

	// @Test
	public void testOauthInvoker() {

		String url = "http://localhost:8080/TeslaServer/rest";
		String grantType = "password";
		String clientId = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
		String clientSecret = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";
		String email = "usuario@gmail.com";
		String password = "passwordUser123";

		try {

			TeslaInvoker teslaInvoker = new TeslaInvoker(url, grantType,
					clientId, clientSecret, email, password);
			OauthInvoker oauthInvoker = teslaInvoker.getOauthInvoker();
			OauthResponse oauthResponse = oauthInvoker.callOauthServer();
			assertNotNull(oauthResponse.getAccess_token());

		} catch (Exception e) {
			assertFalse(true);
		}

	}

}