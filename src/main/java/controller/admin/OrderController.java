package controller.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import converter.OrderDTOConverter;
import dao.OrderDAO;
import dao.OrderedProductDAO;
import dao.UserDAO;
import entity.Order;
import entity.Ordered_Product;
import entity.User;
import model.dto.OrderDTO;

@WebServlet(urlPatterns = {"/admin/edit-order", "/admin/delete-order", "/admin/save-edit-order", "/admin/searchOrder"})
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/admin/edit-order")) {
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
					Order order = OrderDAO.getById(id);				
					List<Ordered_Product> ordered_Products = OrderedProductDAO.getByOrderId(order.getId());
					
					OrderDTO orderDTO = OrderDTOConverter.convertToDTO(order, ordered_Products);
									
					req.setAttribute("orderDTO", orderDTO);
					
					req.setAttribute("admin", admin);

					req.getRequestDispatcher("/admin/edit-order.jsp").forward(req, resp);
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");			
		}
		
		else if(requestURI.endsWith("/admin/delete-order")) {
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
					OrderDAO.deleteById(id);
					
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
		
		else if(requestURI.endsWith("/admin/save-edit-order")) {
			Long id = Long.parseLong(req.getParameter("id"));
			Integer status = Integer.parseInt(req.getParameter("status"));
			
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
					Order order = OrderDAO.getById(id);
					order.setStatus(status);
					
					OrderDAO.updateOrder(order);
					resp.sendRedirect("/BTL_WEB/admin/orders");
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/searchOrder")) {
			Timestamp fromDate = Timestamp.valueOf(req.getParameter("fromDate") + " 00:00:00");
	        Timestamp toDate = Timestamp.valueOf(req.getParameter("toDate") + " 23:59:59");
	        
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
					
					List<Order> orders = OrderDAO.getOrdersByDateRange(fromDate, toDate);
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
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
