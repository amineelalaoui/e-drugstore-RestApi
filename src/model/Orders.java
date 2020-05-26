package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date orderDate;
	private String orderState;
	
	@ManyToMany
	private List<Product> productList;
	
	@OneToOne
	private Client client;

	public Orders() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public JSONObject getJSON() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("orderDate", orderDate);
		object.put("orderState", orderState);
		if(productList!=null) {
			JSONArray productArray = new JSONArray();
			for(Product p: productList ) {
				productArray.add(p.getJSON());
			}
			object.put("productList", productArray);
		}
		object.put("client", client).toString();
		
		return object;
	}
	
	
	
	
}
