package controller;

import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Catalog;
import service.CatalogService;

@Path("catalog")
@RequestScoped
public class CatalogController {
	
	@EJB
	private CatalogService catalogService;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCatalog(Catalog catalog) {
		return catalogService.addCatalog(catalog);
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCatalogs() {
		
		Collection<Catalog> catalogs = catalogService.getCatalogs();
		StringBuilder body = new StringBuilder();
		body.append("[");
		for(Catalog catalog : catalogs) {
			body.append("\n");
			body.append(catalog.toString());
			body.append(",\n");
		}
		if(catalogs!=null)
			body.delete(body.lastIndexOf(","), body.lastIndexOf(",")+1);
		body.append("]");
		return Response.status(Response.Status.OK)
	            .entity(catalogs!=null? body.toString() : "")
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
}
