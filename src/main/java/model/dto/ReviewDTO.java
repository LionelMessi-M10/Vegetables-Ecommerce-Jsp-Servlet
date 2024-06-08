package model.dto;

import java.sql.Timestamp;

import entity.Product;
import entity.User;

public class ReviewDTO {

	private Long id;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private String content;
	private Integer star;
	private Product product;
	private User user;

	public ReviewDTO(Long id, Timestamp createdAt, Timestamp modifiedAt, String content, Integer star, Product product,
			User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.content = content;
		this.star = star;
		this.product = product;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
