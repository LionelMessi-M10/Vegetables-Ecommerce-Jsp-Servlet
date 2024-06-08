package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import converter.CategoryConverter;
import converter.OrderDTOConverter;
import converter.ProductConverter;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ReviewDAO;
import entity.Cart;
import entity.Cart_Item;
import entity.Category;
import entity.Order;
import entity.Ordered_Product;
import entity.Product;
import entity.Review;
import entity.User;
import model.dto.CategoryDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;

@WebServlet(urlPatterns = {"/web/shop/category", "/web/addToCart", "/web/cart/delete-from-cart", "/web/product_detail", "/web/review", "/web/buyNow", 
		"/web/checkout-page", "/web/checkout-from-cart"})
public class ShopController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/web/shop/category")) {
			Long id = Long.parseLong(req.getParameter("id"));
			
			List<Product> products = ProductDAO.getProductPage(1L);
			List<ProductDTO> productDTOs = new ArrayList<>();
			
			for(Product it : products) {
				if(it.getCategoryId() == id) productDTOs.add(ProductConverter.convertToDTO(it));
			}
			
			List<Category> categories = CategoryDAO.getAllCategory();
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			
			for(Category it : categories) {
				categoryDTOs.add(CategoryConverter.convertToDTO(it));
			}
			
			Long totalProduct = ProductDAO.selectTotalProduct();
			Long endPage = totalProduct / 9 + (totalProduct % 9 == 0 ? 0 : 1);
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) {
					req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
				}
			}
			
			req.setAttribute("productDTOS", productDTOs);
			req.setAttribute("categoryDTOs", categoryDTOs);
			req.setAttribute("currentPage", 1);
			req.setAttribute("endPage", endPage);
			
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
		}
		
		else if(requestURI.endsWith("/web/addToCart")) {
			req.setCharacterEncoding("UTF-8");
			Long id = Long.parseLong(req.getParameter("id"));
			Long quantity = 1L;
			if(req.getParameter("quantity") != null) quantity = Long.parseLong(req.getParameter("quantity"));
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				
				Product product = ProductDAO.getById(id);
				
				if(CartDAO.getByCustomerId(user.getId()) == null) {
					CartDAO.addProductToCart(product, user);
					
					Cart_Item cart_Item = new Cart_Item(null, quantity, CartDAO.getByCustomerId(user.getId()).getId(), id);
					CartItemDAO.addProductToCartItem(cart_Item);
				}
				else {
					Cart cart = CartDAO.getByCustomerId(user.getId());
					
					List<Cart_Item> cart_Items = CartItemDAO.getByCartId(cart.getId());
					
					for(Cart_Item it : cart_Items) {
						if(it.getProductId() == id) {
							it.setQuantity(it.getQuantity() + quantity);
							CartItemDAO.updateCartItem(it);
							break;
						}
					}
				}
				
				resp.sendRedirect("/BTL_WEB/web/cart");
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/cart/delete-from-cart")) {
			Long id = Long.parseLong(req.getParameter("id"));
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				
				CartItemDAO.deleteCartItemById(id);
				
				resp.sendRedirect("/BTL_WEB/web/cart");
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/product_detail")) {
			Long id = Long.parseLong(req.getParameter("id"));
			
			Product product = ProductDAO.getById(id);
			
			ProductDTO productDTO = ProductConverter.convertToDTO(product);
			
			List<Category> categories = CategoryDAO.getAllCategory();
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			
			for(Category it : categories) {
				if(it.getStatus() == 1) categoryDTOs.add(CategoryConverter.convertToDTO(it));
			}
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				req.setAttribute("user", user);
				req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
			}
			req.setAttribute("productDTO", productDTO);
			req.setAttribute("categoryDTOs", categoryDTOs);
			
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/product-detail.jsp").forward(req, resp);
		}
		
		else if(requestURI.endsWith("/web/review")) {
			req.setCharacterEncoding("UTF-8");
			Long productId = Long.parseLong(req.getParameter("product_id"));
			String content = req.getParameter("content");
			Integer rating = Integer.parseInt(req.getParameter("rating"));
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				Review review = new Review(null, null, null, content, rating, productId, user.getId());
				ReviewDAO.insertReview(review);
				
				Product product = ProductDAO.getById(productId);
				
				ProductDTO productDTO = ProductConverter.convertToDTO(product);
				
				List<Category> categories = CategoryDAO.getAllCategory();
				List<CategoryDTO> categoryDTOs = new ArrayList<>();
				
				for(Category it : categories) {
					categoryDTOs.add(CategoryConverter.convertToDTO(it));
				}
				
				req.setAttribute("productDTO", productDTO);
				req.setAttribute("categoryDTOs", categoryDTOs);
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/product-detail.jsp").forward(req, resp);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/buyNow")) {
			req.setCharacterEncoding("UTF-8");
			Long id = Long.parseLong(req.getParameter("id"));
			Long quantity = Long.parseLong(req.getParameter("quantity"));
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
								
				if(id != null) {
					
					Order order = new Order(null, 0, null, user.getId());
					List<Ordered_Product> ordered_Products = List.of(new Ordered_Product(null, quantity, null, id));
					
					OrderDTO orderDTO = OrderDTOConverter.convertToDTO(order, ordered_Products);
					session.setAttribute("orderDTO", orderDTO);
				}
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/checkout-page")) {
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				OrderDTO orderDTO = (OrderDTO) session.getAttribute("orderDTO");
				
				session.setAttribute("orderDTO", orderDTO);
				req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/checkout.jsp").forward(req, resp);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/checkout-from-cart")) {
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				Cart cart = CartDAO.getByCustomerId(user.getId());
				List<Cart_Item> cart_Items = CartItemDAO.getByCartId(cart.getId());
			
				Order order = new Order(null, 0, null, user.getId());
				List<Ordered_Product> ordered_Products = new ArrayList<>();
				
				for(Cart_Item it : cart_Items) {
					ordered_Products.add(new Ordered_Product(null, it.getQuantity(), null, it.getProductId()));
				}
				
				OrderDTO orderDTO = OrderDTOConverter.convertToDTO(order, ordered_Products);
					
				session.setAttribute("orderDTO", orderDTO);
				resp.sendRedirect("/BTL_WEB/web/checkout-page");
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
