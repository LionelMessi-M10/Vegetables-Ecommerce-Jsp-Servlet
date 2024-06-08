package entity;

import java.sql.Timestamp;

public class Review extends Base {

	private String content;
	private Integer star;
	private Long productId;
	private Long customerId;

	public Review(Long id, Timestamp createdAt, Timestamp modifiedAt, String content, Integer star,
			Long productId, Long customerId) {
		super(id, createdAt, modifiedAt);
		this.content = content;
		this.star = star;
		this.productId = productId;
		this.customerId = customerId;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
