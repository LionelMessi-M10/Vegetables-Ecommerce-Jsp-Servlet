package entity;

public class Cart_Item {

	private Long id;
	private Long quantity;

	private Long cartId;
	private Long productId;

	public Cart_Item(Long id, Long quantity, Long cartId, Long productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cartId = cartId;
		this.productId = productId;
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

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
