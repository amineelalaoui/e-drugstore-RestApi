package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	private List<Product> productList;

	public Cart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public void addProduct(Product p) {
		//TODO: add a product to the cart
	}
	
	public boolean deleteProduct(Product p) {
		//TODO : delete a product from the card
		return false;
	}
	
	public float getCartTotal() {
		//TODO : return the total price of the cart products
		return 0f;
	}
	
	
}
