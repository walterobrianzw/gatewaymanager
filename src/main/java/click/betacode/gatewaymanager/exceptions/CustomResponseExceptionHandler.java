package click.betacode.gatewaymanager.exceptions;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomResponseExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex){
		ErrorDetails errorDetails=new ErrorDetails(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(GatewayNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleGatewayNotFoundException(GatewayNotFoundException gnfe){
		ErrorDetails errorDetails=new ErrorDetails(gnfe.getMessage(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PeripheralNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handlePeripheralNotFoundException(PeripheralNotFoundException pnfe){
		ErrorDetails errorDetails=new ErrorDetails(pnfe.getMessage(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddPeripheralToGatewayException.class)
	public final ResponseEntity<ErrorDetails> handleAddPeripheralToGatewayException(AddPeripheralToGatewayException aptge){
		ErrorDetails errorDetails=new ErrorDetails(aptge.getMessage(),HttpStatus.BAD_GATEWAY);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.TOO_MANY_REQUESTS);
	}
	@ExceptionHandler(GatewayInvalidIpAddressException.class)
	public final ResponseEntity<ErrorDetails> handleGatewayInvalidIpAddressException(GatewayInvalidIpAddressException giie){
		ErrorDetails errorDetails=new ErrorDetails(giie.getMessage(),HttpStatus.BAD_GATEWAY);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(GatewayNotSavedException.class)
	public final ResponseEntity<ErrorDetails> handleGatewayNotSavedException(GatewayNotSavedException gnse){
		ErrorDetails errorDetails=new ErrorDetails(gnse.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {

		ErrorDetails errorDetails=new ErrorDetails(ex.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}

}
