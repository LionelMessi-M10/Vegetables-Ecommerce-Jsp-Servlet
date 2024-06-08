package entity;

public class Ordered_Product {

	private Long id;
	private Long quantity;

	private Long orderId;
	private Long productId;

	public Ordered_Product(Long id, Long quantity, Long orderId, Long productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.orderId = orderId;
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
