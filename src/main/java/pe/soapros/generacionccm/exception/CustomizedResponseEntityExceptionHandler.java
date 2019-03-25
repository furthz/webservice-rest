package pe.soapros.generacionccm.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "pe.soapros.generacionccm")
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(CustomizedResponseEntityExceptionHandler.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.debug(ADMIN_USER, "ResponseEntity", request, ex);

		List<String> errores = new ArrayList<String>();
		errores.add(request.getDescription(false).toString());
		logger.debug(ADMIN_USER, "Errores", errores);

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.toString(), errores);
		logger.debug(ADMIN_USER, "exceptionResponse", exceptionResponse);

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.debug(ADMIN_USER, "handleHttpMessageNotReadable", ex, headers, status, request);

		List<String> errores = new ArrayList<String>();
		errores.add(ex.getMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validación Fallida", errores);
		logger.debug(ADMIN_USER, "exceptionResponse", exceptionResponse);

		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler(StudentNotFoundException.class) public final
	 * ResponseEntity<Object> handleUserNotFoundException(StudentNotFoundException
	 * ex, WebRequest request) { ExceptionResponse exceptionResponse = new
	 * ExceptionResponse(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity(exceptionResponse,
	 * HttpStatus.NOT_FOUND); }
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.debug(ADMIN_USER, "handleMethodArgumentNotValid", ex, headers, status, request);

		List<String> errores = new ArrayList<String>();

		for (ObjectError e : ex.getBindingResult().getAllErrors()) {
			errores.add(e.getDefaultMessage());
		}
		logger.debug(ADMIN_USER, "Errores", errores);

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validación Fallida", errores);
		logger.debug(ADMIN_USER, "exceptionResponse", exceptionResponse);

		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
