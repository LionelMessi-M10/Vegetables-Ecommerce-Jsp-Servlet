package converter;

import dao.CartItemDAO;
import entity.Cart_Item;
import model.dto.CartItemDTO;

public class CartItemConverter {

	public static CartItemDTO convertToDTO(Cart_Item cart_Item) {
		CartItemDTO cartItemDTO = new CartItemDTO(cart_Item.getId(), cart_Item.getQuantity(), null);
		
		cartItemDTO.setProduct(CartItemDAO.getProductFromCartItem(cart_Item.getProductId()));
		
		return cartItemDTO;
	}
}
