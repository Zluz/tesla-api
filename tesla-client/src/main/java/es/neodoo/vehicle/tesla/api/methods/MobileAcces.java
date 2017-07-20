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
import es.neodoo.vehicle.tesla.api.params.MobileAccesResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class MobileAcces {

	private final static Logger log = Logger
			.getLogger(MobileAcces.class.getName());

	public static final String URL_MOBILE_ACCESS = "/mobile_enabled";

	private TeslaInvoker teslaInvoker;

	public MobileAcces(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public TeslaInvoker getTeslaInvoker() {
		return teslaInvoker;
	}

	public void setTeslaInvoker(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public MobileAccesResponse execute(int vehicleId)
			throws OauthInvokerException, TeslaInvokerException {

		MobileAccesResponse mobileAccesResponse = null;

		try {

			String accessToken = teslaInvoker.getAccessToken();
			Client client = Client.create();
			WebResource webResource = client.resource(
					teslaInvoker.getUri() + "/" + TeslaInvoker.URL_PATH_VEHICLES
							+ "/" + vehicleId + URL_MOBILE_ACCESS);

			ClientResponse response = webResource
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header(OauthInvoker.HEADER_AUTHORIZATION,
							OauthInvoker.HEADER_AUTHORIZATION_BEARER + " "
									+ accessToken)
					.get(ClientResponse.class);

			String output = response.getEntity(String.class);
			mobileAccesResponse = MobileAccesResponse.toObject(output);

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error oauth : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking mobile acces service: " + e.getMessage());
		}

		return mobileAccesResponse;

	}

}