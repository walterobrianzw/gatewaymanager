package click.betacode.gatewaymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.TOO_MANY_REQUESTS)
public class AddPeripheralToGatewayException extends RuntimeException{

	
	public AddPeripheralToGatewayException(String message){
		super(message);
	}
}
