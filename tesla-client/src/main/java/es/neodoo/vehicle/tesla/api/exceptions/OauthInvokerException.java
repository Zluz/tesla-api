package es.neodoo.vehicle.tesla.api.exceptions;

@SuppressWarnings("serial")
public class OauthInvokerException extends Exception {

	public OauthInvokerException(Exception msg) {
		super(msg);
	}

	public OauthInvokerException(String msg) {
		super(msg);
	}

}
