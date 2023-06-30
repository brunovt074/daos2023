/**
 * 
 */
package com.tsti.excepcion;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tsti.rest.error.VueloErrorInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * @author Bruno
 *
 */
@ControllerAdvice
public class VueloErrorHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
        
    	if (ex instanceof MethodArgumentNotValidException) {
            
    		BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();

            StringBuilder errorMessage = new StringBuilder();
            fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() + " "));

            VueloErrorInfo errorInfo = new VueloErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), ((ServletWebRequest) request).getRequest().getRequestURI());

            return handleExceptionInternal(ex, errorInfo, headers, HttpStatus.BAD_REQUEST, request);
        
    	} else if (ex instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex).getConstraintViolations();

            StringBuilder errorMessage = new StringBuilder();
            violations.forEach(violation -> errorMessage.append(violation.getPropertyPath() + " " + violation.getMessage() + " "));

            VueloErrorInfo errorInfo = new VueloErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), ((ServletWebRequest) request).getRequest().getRequestURI());

            return handleExceptionInternal(ex, errorInfo, headers, HttpStatus.BAD_REQUEST, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}

