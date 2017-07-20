package es.neodoo.vehicle.tesla.api.exceptions;

@SuppressWarnings("serial")
public class TeslaInvokerException extends Exception {

	public TeslaInvokerException(String msg) {
		super(msg);
	}

	public TeslaInvokerException(Exception msg) {
		super(msg);
	}

}
