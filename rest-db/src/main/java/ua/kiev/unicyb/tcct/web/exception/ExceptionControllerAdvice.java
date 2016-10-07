package ua.kiev.unicyb.tcct.web.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Denys Storozhenko.
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {RestException.class, RuntimeException.class, Exception.class})
	public ResponseEntity<RestExceptionDto> handleError(final HttpServletRequest request, Exception e) {
		Logger logger = LogManager.getLogger(e);
		logger.error(e.getMessage(), e);
		RestExceptionDto dto = RestExceptionDto.toDto(e);
		dto.setErrorOrigin(getErrorOrigin(request));
		return new ResponseEntity<>(dto, dto.getHttpStatus());
	}

	private String getErrorOrigin(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}
}
