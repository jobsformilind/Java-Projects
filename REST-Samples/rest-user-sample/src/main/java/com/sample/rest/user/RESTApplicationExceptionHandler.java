package com.sample.rest.user;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RESTApplicationExceptionHandler implements ExceptionMapper<Exception> {

	public Response toResponse(Exception exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}
}
