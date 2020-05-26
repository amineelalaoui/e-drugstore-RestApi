package controller;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Client;
import service.ClientService;

@Path("/client")
@RequestScoped
public class ClientController {
	
	@EJB
	private ClientService clientService;

	
	@Path("/add")
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addClient(Client c) {
		return clientService.addClient(c);
	}
	
	@Path("/login/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@PathParam("email") String email,@PathParam("password") String password) {
		return clientService.authenticate(email, password);
	}

}
