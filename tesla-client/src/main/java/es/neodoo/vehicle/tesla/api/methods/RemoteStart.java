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
import es.neodoo.vehicle.tesla.api.params.RemoteStartResponse;
import es.neodoo.vehicle.tesla.invoker.OauthInvoker;
import es.neodoo.vehicle.tesla.invoker.TeslaInvoker;

public class RemoteStart {

	private final static Logger log = Logger
			.getLogger(RemoteStart.class.getName());

	public static final String URL_REMOTE_START = "/command/remote_start_drive";

	private static final String PARAM_PASSWORD = "password";

	private TeslaInvoker teslaInvoker;

	public RemoteStart(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public TeslaInvoker getTeslaInvoker() {
		return teslaInvoker;
	}

	public void setTeslaInvoker(TeslaInvoker teslaInvoker) {
		this.teslaInvoker = teslaInvoker;
	}

	public RemoteStartResponse execute(int vehicleId, String password)
			throws OauthInvokerException, TeslaInvokerException {

		RemoteStartResponse remoteStartResponse = null;

		try {

			String accessToken = teslaInvoker.getAccessToken();
			Client client = Client.create();
			WebResource webResource = client.resource(teslaInvoker.getUri()
					+ "/" + TeslaInvoker.URL_PATH_VEHICLES + "/" + vehicleId
					+ URL_REMOTE_START + "?" + PARAM_PASSWORD + "=" + password);

			ClientResponse response = webResource
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header(OauthInvoker.HEADER_AUTHORIZATION,
							OauthInvoker.HEADER_AUTHORIZATION_BEARER + " "
									+ accessToken)
					.post(ClientResponse.class);

			String output = response.getEntity(String.class);
			remoteStartResponse = RemoteStartResponse.toObject(output);

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error invoking oauth : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking remote start service: " + e.getMessage());
		}

		return remoteStartResponse;

	}

}
