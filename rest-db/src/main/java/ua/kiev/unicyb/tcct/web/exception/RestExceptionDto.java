package ua.kiev.unicyb.tcct.web.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author Denys Storozhenko.
 */
public class RestExceptionDto {
	private String errorOrigin;

	private String definition;

	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorOrigin() {
		return errorOrigin;
	}

	public void setErrorOrigin(String errorOrigin) {
		this.errorOrigin = errorOrigin;
	}

	public static RestExceptionDto toDto(Exception e) {
		RestExceptionDto dto = new RestExceptionDto();
		dto.setDefinition(e.getMessage());
		if (e instanceof RestException) {
			dto.setHttpStatus(((RestException) e).getHttpStatus());
		}
		return dto;
	}
}
