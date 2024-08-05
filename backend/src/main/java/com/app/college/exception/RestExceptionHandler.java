package com.app.college.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.college.payload.response.ApiResponse;
import com.app.college.payload.response.ErrorResponse;


@RestControllerAdvice
public class RestExceptionHandler {
	
	 Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
	
		
		@ExceptionHandler(MissingServletRequestParameterException.class)
		public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(true, ex.getMessage(), ex.getClass().getSimpleName(),null),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(PropertyReferenceException.class)
		public ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException ex){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(true, ex.getMessage(), ex.getClass().getSimpleName(),null),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> handleInvalidPass(final Throwable exception) {
		log.error("BadCredentialsException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());	
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ApiResponse> handleInvalidUser(final Throwable exception) {
		log.error("DisabledException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> accessDenied(final Throwable exception) {
		log.error("AccessDeniedException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse> constrailVoilet(final Throwable exception) {
		log.error("ConstraintViolationException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> constraitVoilet(final Throwable exception) {
		log.error("DataIntegrityViolationException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> handleNotSupport(final Throwable exception) {
		log.error("HttpRequestMethodNotSupportedException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(final Throwable exception) {
		log.error("HttpMessageNotReadableException exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

		Map<Integer,Object> map=new HashMap<>();
		 Integer i=0;
		 for(ObjectError error:exception.getBindingResult().getAllErrors()) {
				String fieldName= ((FieldError) error).getField();
			 	String msg=error.getDefaultMessage();
			 	map.put(i,fieldName+" : "+msg);
			 	log.error("MethodArgumentNotValidException exception occured - {}", i,fieldName+" : "+msg);
			 	i++;
		 }
			return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handle(final Throwable exception) {
		log.error("Exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = TokenRefreshException.class)
	  @ResponseStatus(HttpStatus.FORBIDDEN)
	  public ResponseEntity<ApiResponse> handleTokenRefreshException(TokenRefreshException exception) {
	    log.error("Exception occured - {}", exception.getMessage());
		Map<Integer,Object> map=new HashMap<>();
		map.put(0, exception.getMessage());
		return new ResponseEntity<>(new ApiResponse(true,exception.getClass().getSimpleName(),map), HttpStatus.FORBIDDEN);
	  }

}
