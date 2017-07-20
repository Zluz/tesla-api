package es.neodoo.vehicle.tesla.api.methods;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import es.neodoo.vehicle.tesla.api.exceptions.OauthInvokerException;
import es.neodoo.vehicle.tesla.api.exceptions.TeslaInvokerException;
import es.neodoo.vehicle.tesla.api.params.SetValetModeResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class SetValetMode {

	private final static Logger log = Logger
			.getLogger(SetValetMode.class.getName());

	private static final String PARAM_ONOFF = "onoff";

	private static final String PARAM_PIN = "pin";

	public static final String URL_SET_VALET_MODE = "/command/set_valet_mode";

	private TeslaInvoker teslaInvoker;

	public SetValetMode(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public TeslaInvoker getTeslaInvoker() {
		return teslaInvoker;
	}

	public void setTeslaInvoker(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public SetValetModeResponse execute(int vehicleId, boolean on, int pin)
			throws OauthInvokerException, TeslaInvokerException {

		SetValetModeResponse setValetModeResponse = null;

		try {

			String accessToken = teslaInvoker.getAccessToken();
			Client client = Client.create();
			WebResource webResource = client.resource(teslaInvoker.getUri()
					+ "/" + TeslaInvoker.URL_PATH_VEHICLES + "/" + vehicleId
					+ URL_SET_VALET_MODE + "?" + PARAM_ONOFF + "=" + on + "&"
					+ PARAM_PIN + "=" + pin);

			ClientResponse response = webResource
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header(OauthInvoker.HEADER_AUTHORIZATION,
							OauthInvoker.HEADER_AUTHORIZATION_BEARER + " "
									+ accessToken)
					.post(ClientResponse.class);

			String output = response.getEntity(String.class);
			setValetModeResponse = SetValetModeResponse.toObject(output);

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error invoking oauth : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking climate settings service: "
							+ e.getMessage());
		}

		return setValetModeResponse;

	}

}