package converter;

import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ReviewDAO;
import entity.Product;
import entity.Review;
import model.dto.ProductDTO;
import model.dto.ReviewDTO;

public class ProductConverter {

	public static ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), 
				product.getQuantity(), product.getShortDesc(), product.getDetailDesc(), product.getImage(), 
				product.getStatus(), product.getCreatedAt(), product.getModifiedAt(), null, null);
		
		productDTO.setCategory(CategoryDAO.getByProductId(product.getId()));
		
		List<Review> reviews = ReviewDAO.getByProductId(product.getId());
		List<ReviewDTO> reviewDTOs = new ArrayList<>();
		
		for(Review it : reviews) {
			reviewDTOs.add(ReviewConverter.convertToDTO(it));
		}
		
		productDTO.setReviewDTOs(reviewDTOs);
		
		return productDTO;
	}
}
