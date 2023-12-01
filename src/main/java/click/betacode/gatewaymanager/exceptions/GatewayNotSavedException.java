package click.betacode.gatewaymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class GatewayNotSavedException extends RuntimeException {
	public GatewayNotSavedException(String message) {
		 super(message);
	 }
}
