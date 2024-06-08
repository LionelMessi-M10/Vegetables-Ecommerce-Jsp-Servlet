package model.dto;

import entity.Product;

public class OrderedProductDTO {

	private Long id;
	private Long quantity;
	private Product product;

	public OrderedProductDTO(Long id, Long quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
