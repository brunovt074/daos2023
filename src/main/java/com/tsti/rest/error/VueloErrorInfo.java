/**
 * 
 */
package com.tsti.rest.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsti.excepcion.VueloException;

/**
 * @author Bruno
 *
 */
public class VueloErrorInfo {

	   @JsonProperty("message")
	   private String message;
	   @JsonProperty("status_code")
	   private int statusCode;
	   @JsonProperty("uri")
	   private String uriRequested;

	   public VueloErrorInfo(VueloException exception, String uriRequested) {
	       this.message = exception.getMessage();
	       this.statusCode = exception.getStatusCode();
	       this.uriRequested = uriRequested;
	   }

	   public VueloErrorInfo(int statusCode, String message, String uriRequested) {
	       this.message = message;
	       this.statusCode = statusCode;
	       this.uriRequested = uriRequested;
	   }

	   public String getMessage() {
	       return message;
	   }

	   public int getStatusCode() {
	       return statusCode;
	   }

	   public String getUriRequested() {
	       return uriRequested;
	   }
}
