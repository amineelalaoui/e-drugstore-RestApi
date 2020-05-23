package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Catalog> catalogList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category productCategory;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cart> carts;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Orders> orders;
	
	private String description;
	private String designation;
	private float price;
	private String photo;
	public Product() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Catalog> getCatalogList() {
		return catalogList;
	}
	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}
	public Category getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", catalogList=" + catalogList + ", productCategory=" + productCategory
				+ ", carts=" + carts + ", orders=" + orders + ", description=" + description + ", designation="
				+ designation + ", price=" + price + ", photo=" + photo + "]";
	}
	
	public JSONObject getJSON() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", id);
			jo.put("productCategory", productCategory);
			jo.put("description", description);
			jo.put("designation", designation);
			jo.put("price", price);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}
	
	
	
}
