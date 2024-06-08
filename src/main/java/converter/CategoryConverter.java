package converter;

import dao.ProductDAO;
import entity.Category;
import model.dto.CategoryDTO;

public class CategoryConverter {

	public static CategoryDTO convertToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getCategoryName(), category.getStatus(), null);
		
		categoryDTO.setProducts(ProductDAO.getProductByCategoryId(category.getId()));
		
		return categoryDTO;
	}
}
