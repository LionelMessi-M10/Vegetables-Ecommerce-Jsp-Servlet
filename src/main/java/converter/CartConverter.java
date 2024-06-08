package converter;

import java.util.ArrayList;
import java.util.List;

import dao.CartItemDAO;
import dao.UserDAO;
import entity.Cart;
import entity.Cart_Item;
import model.dto.CartDTO;
import model.dto.CartItemDTO;

public class CartConverter {

	public static CartDTO convertDTO(Cart cart) {
		CartDTO cartDTO = new CartDTO(cart.getId(), cart.getCreatedAt(), null, null);
		
		cartDTO.setUser(UserDAO.getById(cart.getCustomerId()));
		
		List<Cart_Item> cart_Items = CartItemDAO.getByCartId(cart.getId());
		List<CartItemDTO> cartItemDTOs = new ArrayList<>();
		
		for(Cart_Item it : cart_Items) {
			cartItemDTOs.add(CartItemConverter.convertToDTO(it));
		}
		
		cartDTO.setCartItemDTOs(cartItemDTOs);
		
		return cartDTO;
	}
}
