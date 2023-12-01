package click.betacode.gatewaymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class PeripheralNotFoundException extends RuntimeException {
	 public PeripheralNotFoundException(String message) {
		 super(message);
	 }
}
