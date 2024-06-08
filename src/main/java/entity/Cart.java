package entity;

import java.sql.Timestamp;

public class Cart {

	private Long id;
	private Timestamp createdAt;

	private Long customerId;

	public Cart(Long id, Timestamp createdAt, Long customerId) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.customerId = customerId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
