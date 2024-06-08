package model.dto;

import java.sql.Timestamp;
import java.util.List;

import entity.Category;

public class ProductDTO {

	private Long id;
	private String productName;
	private Double price;
	private Long quantity;
	private String shortDesc;
	private String detailDesc;
	private String image;
	private Integer status;
	private Timestamp createdAt;
	private Timestamp modifiedAt;

	private Category category;
	private List<ReviewDTO> reviewDTOs;

	public ProductDTO(Long id, String productName, Double price, Long quantity, String shortDesc, String detailDesc,
			String image, Integer status, Timestamp createdAt, Timestamp modifiedAt, Category category,
			List<ReviewDTO> reviewDTOs) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.shortDesc = shortDesc;
		this.detailDesc = detailDesc;
		this.image = image;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.category = category;
		this.reviewDTOs = reviewDTOs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ReviewDTO> getReviewDTOs() {
		return reviewDTOs;
	}

	public void setReviewDTOs(List<ReviewDTO> reviewDTOs) {
		this.reviewDTOs = reviewDTOs;
	}

}
