package ua.kiev.unicyb.tcct.web.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author Denys Storozhenko.
 */
public class RestException extends RuntimeException {
	private HttpStatus httpStatus;

	public RestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
