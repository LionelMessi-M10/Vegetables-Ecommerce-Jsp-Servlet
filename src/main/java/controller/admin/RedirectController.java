package controller.admin;

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
import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderedProductDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Category;
import entity.Order;
import entity.Ordered_Product;
import entity.Product;
import entity.User;
import model.dto.CategoryDTO;
import model.dto.OrderDTO;

@WebServlet(urlPatterns = { "/admin/dashboard", "/admin/users", "/admin/orders", "/admin/products",
		"/admin/categories", "/admin/add-product-page", "/admin/category-edit-page", "/admin/product-edit-page",
		"/admin/user-edit-page"})
public class RedirectController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();

		if (requestURI.endsWith("/admin/dashboard")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					req.setAttribute("admin", admin);
					req.setAttribute("totalProduct", ProductDAO.selectTotalProduct());
					req.setAttribute("totalCustomer", UserDAO.selectTotalCustomer());
					req.setAttribute("earnings", ProductDAO.selectEarnings());
					req.setAttribute("saleProduct", OrderDAO.getTotalSaleProduct());

					req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}

		else if (requestURI.endsWith("/admin/users")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					req.setAttribute("admin", admin);
					req.setAttribute("users", UserDAO.findAll());

					req.getRequestDispatcher("/admin/user-list.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}

		else if (requestURI.endsWith("/admin/orders")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					List<Order> orders = OrderDAO.getAll();
					List<OrderDTO> orderDTOs = new ArrayList<>();
					
					for(Order it : orders) {
						List<Ordered_Product> ordered_Products = OrderedProductDAO.getByOrderId(it.getId());
						
						OrderDTO orderDTO = OrderDTOConverter.convertToDTO(it, ordered_Products);
						orderDTOs.add(orderDTO);
					}
					
					req.setAttribute("orderDTOs", orderDTOs);
					
					req.setAttribute("admin", admin);

					req.getRequestDispatcher("/admin/order-manager.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}

		else if (requestURI.endsWith("/admin/products")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					
					List<Product> products = ProductDAO.getAllProduct();
					
					req.setAttribute("products", products);
					req.setAttribute("admin", admin);

					req.getRequestDispatcher("/admin/product-list.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}

		else if (requestURI.endsWith("/admin/categories")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					List<Category> categories = CategoryDAO.getAllCategory();
					
					List<CategoryDTO> categoryDTOs = new ArrayList<>();
					
					for(Category it : categories) {
						categoryDTOs.add(CategoryConverter.convertToDTO(it));
					}
					
					req.setAttribute("categories", categoryDTOs);
					req.setAttribute("admin", admin);

					req.getRequestDispatcher("/admin/category-list.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/add-product-page")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
									
					req.setAttribute("admin", admin);
					req.setAttribute("categories", CategoryDAO.getAllCategory());
					
					req.getRequestDispatcher("/admin/add-product.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/category-edit-page")) {
			Long id = Long.parseLong(req.getParameter("id"));
			
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					
					Category category = CategoryDAO.getByCategoryId(id);
					CategoryDTO categoryDTO = CategoryConverter.convertToDTO(category);
									
					req.setAttribute("admin", admin);
					req.setAttribute("categoryDTO", categoryDTO);
					
					req.getRequestDispatcher("/admin/edit-category.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/product-edit-page")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					req.setCharacterEncoding("UTF-8");
					Long id = Long.parseLong(req.getParameter("id"));
					
					req.setAttribute("product", ProductConverter.convertToDTO(ProductDAO.getById(id)));
					req.setAttribute("categories", CategoryDAO.getAllCategory());
					req.setAttribute("admin", admin);
	
					req.getRequestDispatcher("/admin/edit-product.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/user-edit-page")) {
			HttpSession session = req.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					req.setCharacterEncoding("UTF-8");
					Long id = Long.parseLong(req.getParameter("id"));
					
					req.setAttribute("user", UserDAO.getById(id));
					req.setAttribute("admin", admin);
	
					req.getRequestDispatcher("/admin/edit-user.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
	}

}
