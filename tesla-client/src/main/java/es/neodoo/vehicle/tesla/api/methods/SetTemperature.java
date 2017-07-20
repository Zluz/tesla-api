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
import es.neodoo.vehicle.tesla.api.params.SetTemperatureResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class SetTemperature {

	private final static Logger log = Logger
			.getLogger(SetTemperature.class.getName());

	private String PARAM_DRIVER_TEMP = "driver_temp";

	private String PARAM_PASSENGER_TEMP = "passenger_temp";

	public static final String URL_SET_TEMPERATURE = "/command/set_temps";

	private TeslaInvoker teslaInvoker;

	public SetTemperature(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public TeslaInvoker getTeslaInvoker() {
		return teslaInvoker;
	}

	public void setTeslaInvoker(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public SetTemperatureResponse execute(int vehicleId, double driverDegC,
			double passDegC)
			throws OauthInvokerException, TeslaInvokerException {

		SetTemperatureResponse setTemperatureResponse = null;

		try {

			String accessToken = teslaInvoker.getAccessToken();
			Client client = Client.create();
			WebResource webResource = client.resource(teslaInvoker.getUri()
					+ "/" + TeslaInvoker.URL_PATH_VEHICLES + "/" + vehicleId
					+ URL_SET_TEMPERATURE + "?" + PARAM_DRIVER_TEMP + "="
					+ driverDegC + "&" + PARAM_PASSENGER_TEMP + "=" + passDegC);

			ClientResponse response = webResource
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header(OauthInvoker.HEADER_AUTHORIZATION,
							OauthInvoker.HEADER_AUTHORIZATION_BEARER + " "
									+ accessToken)
					.post(ClientResponse.class);

			String output = response.getEntity(String.class);
			setTemperatureResponse = SetTemperatureResponse.toObject(output);

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error invoking oauth : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking set temperature service: "
							+ e.getMessage());
		}

		return setTemperatureResponse;

	}

}