package controller;

import java.util.Collection;

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

import model.Orders;
import net.minidev.json.JSONArray;
import service.OrderService;

@Path("order")
@RequestScoped
public class OrderController {

	@EJB
	private OrderService orderService;
	
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {
		Collection<Orders> orders = (Collection<Orders>) orderService.getOrders();
		JSONArray ordersArray = new JSONArray();
		for(Orders order : orders) {
			ordersArray.add(order.getJSON());
		}
		return Response.status(Response.Status.OK)
				   .header("Access-Control-Allow-Origin", "*")
	        	   .header("Access-Control-Allow-Credentials", "true")
		    	   .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		    	   .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				   .entity(ordersArray.toString())
				   .type(MediaType.APPLICATION_JSON)
				   .build(); 
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrder(Orders order) {
		return orderService.addOrder(order);
	}
	
	@GET
	@Path("get/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdersByClient(@PathParam("clientId") long id) {
		Collection<Orders> orders = (Collection<Orders>) orderService.getOrdersByClient(id);
		JSONArray ordersArray = new JSONArray();
		for(Orders order : orders) {
			ordersArray.add(order.getJSON());
		}
		return Response.status(Response.Status.OK)
				   .header("Access-Control-Allow-Origin", "*")
	        	   .header("Access-Control-Allow-Credentials", "true")
		    	   .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		    	   .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				   .entity(ordersArray.toString())
				   .type(MediaType.APPLICATION_JSON)
				   .build(); 
	}
	

}
