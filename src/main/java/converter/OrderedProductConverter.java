package converter;

import dao.ProductDAO;
import entity.Ordered_Product;
import model.dto.OrderedProductDTO;

public class OrderedProductConverter {

	public static OrderedProductDTO convertToDTO(Ordered_Product ordered_Product) {
		OrderedProductDTO orderedProductDTO = new OrderedProductDTO(ordered_Product.getId(), ordered_Product.getQuantity(), null);
		
		orderedProductDTO.setProduct(ProductDAO.getById(ordered_Product.getProductId()));
		
		return orderedProductDTO;
	}
}
