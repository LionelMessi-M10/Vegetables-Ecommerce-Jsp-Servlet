package converter;

import dao.ProductDAO;
import dao.UserDAO;
import entity.Review;
import model.dto.ReviewDTO;

public class ReviewConverter {

	public static ReviewDTO convertToDTO(Review review) {
		
		ReviewDTO reviewDTO = new ReviewDTO(review.getId(), review.getCreatedAt(), review.getModifiedAt(), review.getContent(), 
				review.getStar(), ProductDAO.getById(review.getProductId()), UserDAO.getById(review.getCustomerId()));
		
		return reviewDTO;
	}
}
