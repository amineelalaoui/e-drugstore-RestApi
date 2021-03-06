package service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;
import model.Product;

@Stateless
public class CategoryService {

	@PersistenceContext
	private EntityManager em;
	
	public Response addCategory(Category category) {
		try {
			System.out.println(category.getJSON().toString());
			em.persist(category);
		}catch(Exception e){
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}

		return Response.status(Response.Status.OK)
	            .entity(category.getJSON().toString())
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
	
	public Category getCategoryByName(String name) {
		Category category = null;
		System.out.println(name);
		Query qr = em.createQuery("SELECT C From Category C WHERE C.categoryName=:CategoryName");
		qr.setParameter("CategoryName", name);
		try {
			category = (Category) qr.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		return category;
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Category> getAllCategories(){
		return em.createQuery("select c from Category c").getResultList();
		
	}
	
	public Category getCategoryById(Long id) {
		Category p = (Category) em.find(Category.class, id);
		return p;
	}
	
	public boolean deleteById(long id) {
		try {
		Category cat = (Category) em.find(Category.class, id);
			em.remove(cat);
		}catch(Exception e) {
			return false;
		}
		return true;
		
	}
	
	public Response update(Category c) {
		try {
			em.merge(c);
		}catch(Exception e){
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}

		return Response.status(Response.Status.OK)
	            .entity(c.getJSON().toString())
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
}
