package converter;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import dao.UserDAO;
import entity.Order;
import entity.Ordered_Product;
import model.dto.OrderDTO;
import model.dto.OrderedProductDTO;

public class OrderDTOConverter {

	public static OrderDTO convertToDTO(Order order, List<Ordered_Product> ordered_Products) {
		OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCreatedAt(), order.getStatus(), null, null, null);
		
		orderDTO.setUser(UserDAO.getById(order.getCustomerId()));
		
		List<OrderedProductDTO> orderedProductDTOs = new ArrayList<>();
		Double total = 0.0;
		
		for(Ordered_Product it : ordered_Products) {
			orderedProductDTOs.add(new OrderedProductDTO(it.getId(), it.getQuantity(), ProductDAO.getById(it.getProductId())));
			total += it.getQuantity() * ProductDAO.getById(it.getProductId()).getPrice();
		}
		
		orderDTO.setOrderedProductDTOs(orderedProductDTOs);
		orderDTO.setTotal(total);
		
		return orderDTO;
	}
}
