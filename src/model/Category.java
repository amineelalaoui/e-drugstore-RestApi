package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String categoryName;
	private String description;
	private String picturePath;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> productList;

	public Category() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public JSONObject getJSON() {
		JSONObject catalog = new JSONObject();
		try {
			catalog.put("id", id);
			catalog.put("categoryName", categoryName);
			catalog.put("description", description);
			catalog.put("picturePath", picturePath);
			if(productList!=null) {
				JSONArray jsonProduct = new JSONArray();
				for(Product product : productList) {
					jsonProduct.put(product.getJSON());
				}
				catalog.append("productList", jsonProduct);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catalog;

	}
	
	
	
	
}
