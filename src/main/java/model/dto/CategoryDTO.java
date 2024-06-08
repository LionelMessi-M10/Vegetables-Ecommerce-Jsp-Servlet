package model.dto;

import java.util.List;

import entity.Product;

public class CategoryDTO {

	private Long id;
	private String categoryName;
	private Integer status;
	private List<Product> products;

	public CategoryDTO(Long id, String categoryName, Integer status, List<Product> products) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.status = status;
		this.products = products;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
