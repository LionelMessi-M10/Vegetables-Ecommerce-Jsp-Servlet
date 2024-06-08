package model.dto;

import java.sql.Timestamp;
import java.util.List;

import entity.User;

public class OrderDTO {

	private Long id;
	private Timestamp createdAt;
	private Integer status;
	private User user;
	private List<OrderedProductDTO> orderedProductDTOs;
	private Double total;

	public OrderDTO(Long id, Timestamp createdAt, Integer status, User user, List<OrderedProductDTO> orderedProductDTOs,
			Double total) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.status = status;
		this.user = user;
		this.orderedProductDTOs = orderedProductDTOs;
		this.total = total;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderedProductDTO> getOrderedProductDTOs() {
		return orderedProductDTOs;
	}

	public void setOrderedProductDTOs(List<OrderedProductDTO> orderedProductDTOs) {
		this.orderedProductDTOs = orderedProductDTOs;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
