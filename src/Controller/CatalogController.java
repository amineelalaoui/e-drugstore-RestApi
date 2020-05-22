package controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
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
	
	
}
