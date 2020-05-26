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

import model.Category;
import service.CategoryService;

@Path("/category")
@RequestScoped
public class CategoryController {

	@EJB
	private CategoryService categoryService;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category) {
		return categoryService.addCategory(category);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories() {
		JSONArray catgories = new JSONArray();
		for(Category cat : categoryService.getAllCategories()) {
			catgories.put(cat.getJSON());
		}
		return Response.status(Response.Status.OK)
						.entity(catgories.toString())
						.type(MediaType.APPLICATION_JSON)
						.build();
	}
	
	@GET
	@Path("/get/{name}")
	public Response getByName(@PathParam("name") String name) {
		System.out.println(name);
		return Response.status(Response.Status.NOT_FOUND)
				.entity(categoryService.getCategoryByName(name)!= null ? categoryService.getCategoryByName(name).getJSON().toString() : "{\n\t\"message\" : \"not found\"\n}")
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(Category category) {
		return categoryService.update(category);
	}
	

	
	@POST
	@Path("/deletebyid/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCategory(@PathParam("id") long id) {
		if(categoryService.deleteById(id)) {
			return Response.status(Response.Status.OK)
					.entity("{\"success\": \"deleted\"}")
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		return Response.status(Response.Status.OK)
				.entity("{\"error\": \"error occured during the operation\"}")
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	
}
