package es.neodoo.vehicle.tesla.invoker;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.neodoo.vehicle.tesla.api.exceptions.OauthInvokerException;
import es.neodoo.vehicle.tesla.api.exceptions.TeslaInvokerException;
import es.neodoo.vehicle.tesla.api.params.OauthResponse;

public class TeslaInvoker {

	private final static Logger log = Logger
			.getLogger(OauthInvoker.class.getName());

	private static final String URL_API_RELEASE = "api/1";

	private static final String URL_VEHICLES = "vehicles";

	public static final String URL_PATH_VEHICLES = URL_API_RELEASE + "/"
			+ URL_VEHICLES;

	private String uri;

	private OauthInvoker oauthInvoker;

	public TeslaInvoker(String uri, String grantType, String clientId,
			String clientSecret, String email, String password) {

		this.uri = uri;
		// Oauth invoker initialization
		oauthInvoker = new OauthInvoker(uri, grantType, clientId, clientSecret,
				email, password);

	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public OauthInvoker getOauthInvoker() {
		return oauthInvoker;
	}

	public void setOauthInvoker(OauthInvoker oauthInvoker) {
		this.oauthInvoker = oauthInvoker;
	}

	public String getAccessToken()
			throws OauthInvokerException, TeslaInvokerException {

		try {

			OauthResponse oauthResponse = oauthInvoker.callOauthServer();
			String accessToken = oauthResponse.getAccess_token();

			return accessToken;

		} catch (OauthInvokerException e) {
			log.log(Level.SEVERE, "Error oauth : " + e.getMessage());
			throw e;

		}

	}

}