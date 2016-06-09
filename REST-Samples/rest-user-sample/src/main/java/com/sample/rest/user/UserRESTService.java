package com.sample.rest.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/1.0/users")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface UserRESTService {

	@GET
	public Response findUsers();

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") Long id);

	//http://localhost:8091/restapp/rest/1.0/users/name/Milind%20Patil
	//http://localhost:8091/restapp/rest/1.0/users/name/Milind Patil
	@GET
	@Path(value="/name/{user:[a-zA-Z]+([\\s]?[a-zA-Z]+)*}")
	public Response getUser1(@PathParam("user") String userId);

	@GET
	@Path("/user")
	public Response getUser2(String userId);


	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUser(@PathParam("id") Long id, User user);

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addUser(User user);

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id")Long id);
}
