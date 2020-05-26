package service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Client;
import model.Orders;

@Stateless
public class OrderService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Response addOrder(Orders order) {
		try {
			em.persist(order);
			}catch(Exception e) {
				return Response.status(Response.Status.NOT_ACCEPTABLE)
						.header("Access-Control-Allow-Origin", "*")
				        .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .entity("{\n"
			            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
			            		+ "}")
			            .type(MediaType.APPLICATION_JSON)
			            .build();
			}
			
			return Response.status(Response.Status.OK)
						   .header("Access-Control-Allow-Origin", "*")
			        	   .header("Access-Control-Allow-Credentials", "true")
				    	   .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				    	   .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						   .entity(order)
						   .type(MediaType.APPLICATION_JSON)
						   .build();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Orders> getOrders() {
		Query qr = em.createQuery("select o from order o");
		return qr.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Orders> getOrdersByClient(long id) {
		try{
		Query qr = em.createQuery("select o from order o where o.client_id=:id");
		qr.setParameter("id", id);
		return qr.getResultList();
		}catch(Exception e) {
			return null;
		}
	}
	
}
