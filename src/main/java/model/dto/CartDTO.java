package model.dto;

import java.sql.Timestamp;
import java.util.List;

import entity.User;

public class CartDTO {

	private Long id;
	private Timestamp createdAt;
	private User user;
	private List<CartItemDTO> cartItemDTOs;

	public CartDTO(Long id, Timestamp createdAt, User user, List<CartItemDTO> cartItemDTOs) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.user = user;
		this.cartItemDTOs = cartItemDTOs;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItemDTO> getCartItemDTOs() {
		return cartItemDTOs;
	}

	public void setCartItemDTOs(List<CartItemDTO> cartItemDTOs) {
		this.cartItemDTOs = cartItemDTOs;
	}

}
