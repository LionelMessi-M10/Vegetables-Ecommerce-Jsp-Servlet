package entity;

import java.sql.Timestamp;

public class Order {

	private Long id;
	private Integer status;
	private Timestamp createdAt;

	private Long customerId;

	public Order(Long id, Integer status, Timestamp createdAt, Long customerId) {
		super();
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
