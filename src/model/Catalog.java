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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String logo;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> productList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Provider provider;
	
	public Catalog() {
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		JSONObject catalog = new JSONObject();
		try {
			catalog.put("id", id);
			catalog.put("name", name);
			catalog.put("logo", logo);
			if(productList!=null) {
				JSONArray jsonProduct = new JSONArray();
				for(Product product : productList) {
					jsonProduct.put(product.getJSON());
				}
				catalog.append("productList", jsonProduct);
			}
			if(provider!=null)
				catalog.put("provider", provider.getJSON());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalog.toString();
	}
	
	
	
	
	
}
