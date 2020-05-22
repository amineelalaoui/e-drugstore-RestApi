package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Client;

@Stateless
public class ClientService {

	@PersistenceContext(unitName = "MaPU")
	private EntityManager em;
	
	public Response addClient(Client c) {
		try {
			em.persist(c);
		}catch(Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}
		return Response.status(Response.Status.OK)
	            .entity(c)
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}

	
	
}
