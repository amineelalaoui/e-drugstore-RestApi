package service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Catalog;
import model.Provider;

@Stateless
public class CatalogService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Response addCatalog(Catalog catalog) {
		try {
			if(catalog.getProvider()!=null) {
				if(catalog.getId()!=null) {
					Provider p = em.find(Provider.class, catalog.getProvider().getId());
					if(p==null) {
						em.persist(catalog.getProvider());
						em.flush();
					}
					else
						catalog.setProvider(p);
				}
			}
			//todo : save or update instead of persist
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
	
	
	@SuppressWarnings("unchecked")
	public Collection<Catalog> getCatalogs(){
		Query qr = em.createQuery("select c from Catalog c");
		return qr.getResultList();
	}

}
