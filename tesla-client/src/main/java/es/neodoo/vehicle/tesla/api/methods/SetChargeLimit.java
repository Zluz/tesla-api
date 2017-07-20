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
import es.neodoo.vehicle.tesla.api.params.SetChargeLimitResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class SetChargeLimit {

	private final static Logger log = Logger
			.getLogger(SetChargeLimit.class.getName());

	public static final String URL_SET_CHARGE_LIMIT = "/command/set_charge_limit";

	private static final String PARAM_PERCENT = "percent";

	private TeslaInvoker teslaInvoker;

	public SetChargeLimit(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public TeslaInvoker getTeslaInvoker() {
		return teslaInvoker;
	}

	public void setTeslaInvoker(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public SetChargeLimitResponse execute(int vehicleId, int limitValue)
			throws OauthInvokerException, TeslaInvokerException {

		SetChargeLimitResponse setChargeLimitResponse = null;

		try {

			String accessToken = teslaInvoker.getAccessToken();
			Client client = Client.create();
			WebResource webResource = client.resource(
					teslaInvoker.getUri() + "/" + TeslaInvoker.URL_PATH_VEHICLES
							+ "/" + vehicleId + URL_SET_CHARGE_LIMIT + "?"
							+ PARAM_PERCENT + "=" + limitValue);

			ClientResponse response = webResource
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header(OauthInvoker.HEADER_AUTHORIZATION,
							OauthInvoker.HEADER_AUTHORIZATION_BEARER + " "
									+ accessToken)
					.post(ClientResponse.class);

			String output = response.getEntity(String.class);
			setChargeLimitResponse = SetChargeLimitResponse.toObject(output);

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error invoking oauth : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking set charge limit service: "
							+ e.getMessage());
		}

		return setChargeLimitResponse;

	}

}