package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String firstName;
	
	@Column(unique = true)
	private String phoneNumber;
	private String type;
	private String address;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private Date createdAt;
	private Date updatedAt;
	
	
	
	public Client(String name, String firstName, String phoneNumber, String type, String address, String username,
			String password, String email) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.address = address;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "{\n" + "\"id: \"" + "\""  + id  + "\"" + ",\n \"name\": " + "\"" + name + "\""  + ",\n \"firstName\": " + "\"" + firstName + "\" , \n \"phoneNumber\": " +"\"" + phoneNumber
				+ "\" ,\n \"type\":" + "\"" +  type + "\",\n \"address\":" + "\"" + address +  "\",\n \"username\":" + "\"" + username + "\",\n \"email\":" + "\"" +  email + "\"\n}";
	}
	
	
	
	
	
}
