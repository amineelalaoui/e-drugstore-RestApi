package controller;

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

import org.json.JSONArray;

import com.sun.jersey.spi.container.ParamQualifier;

import model.Category;
import model.Product;
import service.ProductService;

@Path("product")
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
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Credentials", "true")
			    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(products.toString())
						.type(MediaType.APPLICATION_JSON)
						.build();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") long id) {
		Product p = productService.getProductById(id);
		if(p!=null)
			return Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
		    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(p.getJSON().toString())
					.type(MediaType.APPLICATION_JSON)
					.build();
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
		        .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .entity("{\n"
	            		+ "\t \"error\": \"no product available with this id\"\n"
	            		+ "}")
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
	
	@GET
	@Path("/getbycat/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByIdCategory(@PathParam("id") long id) {
		Product p = productService.getProductByIdCategory(id);
		if(p!=null)
			return Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
		    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(p.getJSON().toString())
					.type(MediaType.APPLICATION_JSON)
					.build();
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
		        .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .entity("{\n"
	            		+ "\t \"error\": \"no product available with this category id\"\n"
	            		+ "}")
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
	
	

}
