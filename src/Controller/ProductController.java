package controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import model.Product;
import service.ProductService;

@Path("/product")
@RequestScoped
public class ProductController {
	
	@EJB
	private ProductService productService;
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product p) {
		return productService.addProduct(p);
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts(){
		JSONArray products = new JSONArray();
		for(Product p : productService.getAllProduct()) {
			products.put(p.getJSON());
		}
		return Response.status(Response.Status.OK)
						.entity(products.toString())
						.type(MediaType.APPLICATION_JSON)
						.build();
	}
	
	

}
