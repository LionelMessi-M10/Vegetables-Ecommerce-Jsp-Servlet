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

import converter.CartConverter;
import converter.CategoryConverter;
import converter.OrderDTOConverter;
import converter.ProductConverter;
import dao.CartDAO;
import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderedProductDAO;
import dao.ProductDAO;
import entity.Cart;
import entity.Category;
import entity.Order;
import entity.Ordered_Product;
import entity.Product;
import entity.User;
import model.dto.CategoryDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;

@WebServlet(urlPatterns = { "/web/login", "/web/register", "/web/home", "/web/logout", "/web/profile", "/web/shop", "/web/order", "/web/order-detail", 
		"/web/cart", "/web/search" })
public class RedirectController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();

		if (requestURI.endsWith("/web/register")) {
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/register.jsp").forward(req, resp);
		}

		else if (requestURI.endsWith("/web/login")) {
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/login.jsp").forward(req, resp);
		}
		
		else if(requestURI.endsWith("/web/home")) {
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
			}
			
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/home.jsp").forward(req, resp);
		}
		
		else if(requestURI.endsWith("/web/logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
			
			resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/profile")) {
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
						
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
				req.setAttribute("user", user);
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/profile.jsp").forward(req, resp);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		
		}
		
		else if(requestURI.endsWith("/web/shop")) {
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			
			List<Category> categories = CategoryDAO.getAllCategory();
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			
			for(Category it : categories) {
				if(it.getStatus() == 1) categoryDTOs.add(CategoryConverter.convertToDTO(it));
			}
			
			Long totalProduct = ProductDAO.selectTotalProduct();
			Long endPage = totalProduct / 9 + (totalProduct % 9 == 0 ? 0 : 1);
			
			List<Product> products = ProductDAO.getProductPage(1L);
			List<ProductDTO> productDTOs = new ArrayList<>();
			
			for(Product it : products) {
				if(it.getStatus() == 1) productDTOs.add(ProductConverter.convertToDTO(it));
			}
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
			}
			
			req.setAttribute("productDTOS", productDTOs);
			req.setAttribute("categoryDTOs", categoryDTOs);
			req.setAttribute("currentPage", 1);
			req.setAttribute("endPage", endPage);
			
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
		}
		
		else if(requestURI.endsWith("/web/order")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) {
					req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
					req.setAttribute("cartDTO", CartConverter.convertDTO(CartDAO.getByCustomerId(user.getId())));
				}
				
				
				List<Order> orders = OrderDAO.getByCustomerId(user.getId());
				List<OrderDTO> orderDTOs = new ArrayList<>();
				
				for(Order it : orders) {
					List<Ordered_Product> ordered_Products = OrderedProductDAO.getByOrderId(it.getId());
					
					OrderDTO orderDTO = OrderDTOConverter.convertToDTO(it, ordered_Products);
					orderDTOs.add(orderDTO);
				}
				
				req.setAttribute("orderDTOs", orderDTOs);
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/order.jsp").forward(req, resp);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
			
		}
		
		else if(requestURI.endsWith("/web/order-detail")) {
			Long orderId = Long.parseLong(req.getParameter("id"));
			
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) {
					req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
					req.setAttribute("cartDTO", CartConverter.convertDTO(CartDAO.getByCustomerId(user.getId())));
				}
				
				
				Order order = OrderDAO.getById(orderId);				
				List<Ordered_Product> ordered_Products = OrderedProductDAO.getByOrderId(order.getId());
				
				OrderDTO orderDTO = OrderDTOConverter.convertToDTO(order, ordered_Products);
								
				req.setAttribute("orderDTO", orderDTO);
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/order-detail.jsp").forward(req, resp);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/cart")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
				
				Cart cart = CartDAO.getByCustomerId(user.getId());
				
				if(cart != null) {
					req.setAttribute("cartDTO", CartConverter.convertDTO(cart));
					req.setAttribute("totalMoneyInCart", CartDAO.getTotalMoneyInCart(user.getId()));
				}
				else req.setAttribute("cartDTO", null);
				
				resp.setContentType("text/html; charset=UTF-8");
				req.getRequestDispatcher("/web/cart.jsp").forward(req, resp);
			}
			
			else resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/web/search")) {
			req.setCharacterEncoding("UTF-8");
			String key = req.getParameter("key");
			
			List<Product> products = ProductDAO.searchProductByKey(key, 1L);
			
			List<Category> categories = CategoryDAO.getAllCategory();
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			
			for(Category it : categories) {
				categoryDTOs.add(CategoryConverter.convertToDTO(it));
			}
			
			Long totalProduct = (long) products.size();
			Long endPage = totalProduct / 9 + (totalProduct % 9 == 0 ? 0 : 1);
			
			List<ProductDTO> productDTOs = new ArrayList<>();
			
			for(Product it : products) {
				productDTOs.add(ProductConverter.convertToDTO(it));
			}
			
			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
			}
			
			req.setAttribute("productDTOS", productDTOs);
			req.setAttribute("categoryDTOs", categoryDTOs);
			req.setAttribute("currentPage", 1);
			req.setAttribute("endPage", endPage);
			
			resp.setContentType("text/html; charset=UTF-8");
			req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
			
		}
	}

}
