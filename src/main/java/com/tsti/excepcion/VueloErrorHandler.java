/**
 * 
 */
package com.tsti.excepcion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tsti.rest.error.VueloErrorInfo;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Bruno
 *
 */
@ControllerAdvice
public class VueloErrorHandler {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<VueloErrorInfo> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
	
	// get spring errors
       BindingResult result = e.getBindingResult();
       List<FieldError> fieldErrors = result.getFieldErrors();

       // convert errors to standard string
       StringBuilder errorMessage = new StringBuilder();
       fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));

       // return error info object with standard json
       VueloErrorInfo errorInfo = new VueloErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), request.getRequestURI());

       
	   return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
   
   }
}
