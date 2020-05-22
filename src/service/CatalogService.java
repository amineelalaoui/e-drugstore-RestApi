package service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Catalog;

@Stateless
public class CatalogService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Response addCatalog(Catalog catalog) {
		try {
			em.persist(catalog);
		}catch(Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}
		return Response.status(Response.Status.OK)
	            .entity(catalog)
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
	
	public Catalog getCatalogByName(String name) {
		Catalog catalog = em.find(Catalog.class, name);
		return catalog;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Catalog> getCatalogs(){
		Query qr = em.createQuery("select c from catalog c");
		return qr.getResultList();
	}

}
