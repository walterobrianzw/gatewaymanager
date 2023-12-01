package click.betacode.gatewaymanager.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	private String message;

	private HttpStatus status;
	ErrorDetails(String message,HttpStatus status){
		super();
		this.setMessage(message);
		this.setStatus(status);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
