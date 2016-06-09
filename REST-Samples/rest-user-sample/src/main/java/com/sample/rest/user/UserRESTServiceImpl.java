package com.sample.rest.user;

import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;

public class UserRESTServiceImpl implements UserRESTService {
	static Map<Long, User> users = new TreeMap<Long, User>();

	static {
		users.put(100L, new User(100L, "Mandar", "N", "Mandar@sungard.com"));
		users.put(102L, new User(102L, "Milind", "P", "Milind@sungard.com"));
	}

	public Response findUsers() {
		return Response.status(Response.Status.OK).entity(users).build();
	}

	public Response addUser(User user) {
		validateUser(user);
		if (!users.containsKey(user.getId())) {
			users.put(user.getId(), user);
		}
		return Response.status(Response.Status.CREATED).build();
	}

	public Response getUser(Long id) {
		System.out.println("Inside  getUser");
		User user = users.get(id);
		validateUser(user);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	public Response updateUser(Long id, User user) {
		validateIdAndUser(id, user);
		User dbUser = users.get(user.getId());
		dbUser.updateSelf(user);
		return Response.status(Response.Status.OK).build();
	}

	public Response deleteUser(Long id) {
		if (users.containsKey(id)) {
			users.remove(id);
		}
		return Response.status(Response.Status.OK).build();
	}

	private void validateUser(User user) {
		if (user == null || user.getId() == null) {
			throw new RuntimeException("Invalid User");
		}
	}

	private void validateIdAndUser(Long id, User user) {
		validateUser(user);
		if (id == null || !id.equals(user.getId())) {
			throw new RuntimeException("Invalid User to update");
		}
	}

	@Override
	public Response getUser1(String userId) {
		System.out.println("Inside  getUser1 : " + userId);
		User user = users.get(102L);
		validateUser(user);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	@Override
	public Response getUser2(String userId) {
		System.out.println("Inside  getUser2");
		return null;
	}

}
