package click.betacode.gatewaymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
public class GatewayInvalidIpAddressException extends RuntimeException{
	 public GatewayInvalidIpAddressException(String message) {
		 super(message );
	 }

}
