package service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Client;

public class ClientService {

	@PersistenceContext
	private EntityManager em;
	
	public boolean addClient(String firstName, String lastName, String phoneNumber, String type, String address,String email, String username, String password, boolean crypted) {
		Client c = new Client();
		c.setFirstName(firstName);
		c.setName(lastName);
		c.setEmail(email);
		c.setAddress(address);
		c.setPhoneNumber(phoneNumber);
		c.setUsername(username);
		c.setType(type);
		//plain text password
		if(!crypted) {
			//TODO use the bcrypt algorith to hash the password
		}
		em.persist(c);
		//TODO : check existance of the email
		return true;
	}
	
	
}
