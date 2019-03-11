package pe.soapros.generacionccm.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> errores = new ArrayList<String>();
		errores.add(request.getDescription(false).toString());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), errores);
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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

		List<String> errores = new ArrayList<String>();

		for (ObjectError e : ex.getBindingResult().getAllErrors()) {
			errores.add(e.getDefaultMessage());
		}

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validación Fallida", errores);
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
