package service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Product;

@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager em;
	
	public Response addProduct(Product p) {
		try {
		em.persist(p);
		}catch(Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}
		
		return Response.status(Response.Status.OK)
					   .entity(p)
					   .type(MediaType.APPLICATION_JSON)
					   .build();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Product> getAllProduct(){
		Query qr = em.createQuery("select p from Product p");
		return qr.getResultList();
	}
	
}
