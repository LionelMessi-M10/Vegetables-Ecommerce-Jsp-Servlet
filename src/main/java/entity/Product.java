package entity;

import java.sql.Timestamp;

public class Product extends Base {

	private String productName;
	private Double price;
	private Long quantity;
	private String shortDesc;
	private String detailDesc;
	private String image;
	private Integer status;

	private Long categoryId;

	public Product(Long id, Timestamp createdAt, Timestamp modifiedAt, String productName, Double price, Long quantity,
			String shortDesc, String detailDesc, String image, Integer status, Long categoryId) {
		super(id, createdAt, modifiedAt);
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.shortDesc = shortDesc;
		this.detailDesc = detailDesc;
		this.image = image;
		this.status = status;
		this.categoryId = categoryId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
