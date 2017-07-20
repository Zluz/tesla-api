package es.neodoo.vehicle.tesla.invoker;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import es.neodoo.vehicle.tesla.api.exceptions.OauthInvokerException;
import es.neodoo.vehicle.tesla.api.exceptions.TeslaInvokerException;
import es.neodoo.vehicle.tesla.api.params.OauthRequest;
import es.neodoo.vehicle.tesla.api.params.OauthResponse;

public class OauthInvoker {

	private final static Logger log = Logger
			.getLogger(OauthInvoker.class.getName());

	public static final String HEADER_AUTHORIZATION = "Authorization";

	public static final String HEADER_AUTHORIZATION_BEARER = "Bearer";

	public static final String URL_OAUTH_TOKEN = "oauth/token";

	private String uri;

	private OauthRequest oauthRequest;

	private OauthResponse oauthResponse;

	public OauthInvoker(String uri, String grantType, String clientId,
			String clientSecret, String email, String password) {

		this.uri = uri;
		this.oauthRequest = new OauthRequest(clientId, clientSecret, email,
				password);

	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public OauthRequest getOauthRequest() {
		return oauthRequest;
	}

	public void setOauthRequest(OauthRequest oauthRequest) {
		this.oauthRequest = oauthRequest;
	}

	public OauthResponse getOauthResponse() {
		return oauthResponse;
	}

	public void setOauthResponse(OauthResponse oauthResponse) {
		this.oauthResponse = oauthResponse;
	}

	public String getAccessToken() {
		return this.oauthResponse.getAccess_token();
	}

	public boolean isAccessTokenValid() {

		if (oauthResponse != null) {

			Long createdAt = (long) (oauthResponse.getCreated_at()) * 1000;
			Date dateCreated = new Date(createdAt);
			Calendar calendarCreatedAt = Calendar.getInstance();
			calendarCreatedAt.setTime(dateCreated);
			calendarCreatedAt.add(Calendar.MILLISECOND,
					oauthResponse.getExpires_in());
			Calendar calendarNow = Calendar.getInstance();

			if (calendarCreatedAt.after(calendarNow)) {

				return true;

			}

			return false;

		}

		return false;

	}

	public OauthResponse callOauthServer()
			throws OauthInvokerException, TeslaInvokerException {

		try {

			if (isAccessTokenValid()) {

				return oauthResponse;

			}

			else {
				// Call Oauth server
				Client client = Client.create();
				WebResource webResource = client
						.resource(uri + "/" + URL_OAUTH_TOKEN);
				ClientResponse response = webResource
						.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class);

				String output = response.getEntity(String.class);
				this.oauthResponse = OauthResponse.toObject(output);
			}

		} catch (IOException e) {
			log.log(Level.SEVERE,
					"Error invoking Tesla API : " + e.getMessage());
			throw new TeslaInvokerException(
					"Error invoking get an acces token service: "
							+ e.getMessage());
		}

		return oauthResponse;

	}

}