package controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.OrderDAO;
import entity.Cart;
import entity.User;
import model.dto.OrderDTO;

@WebServlet(urlPatterns = {"/web/order-confirm", "/web/cancel-order"})
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/web/order-confirm")) {
			HttpSession session = req.getSession();
			
			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				OrderDTO orderDTO = (OrderDTO) session.getAttribute("orderDTO");
				
				OrderDAO.insertOrder(orderDTO);
				
				Cart cart = CartDAO.getByCustomerId(user.getId());
				if(cart != null) CartDAO.deleteById(cart.getId());
				
				session.removeAttribute("orderDTO");
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.sendRedirect("/BTL_WEB/web/order");
		}
		
		else if(requestURI.endsWith("/web/cancel-order")) {
			Long id = Long.parseLong(req.getParameter("id"));
			
			HttpSession session = req.getSession();
			
			User user = (User) session.getAttribute("loginedUser");
			
			if(user != null) {
				OrderDAO.deleteById(id);
			}
			else resp.sendRedirect("/BTL_WEB/web/login");
			
			resp.setContentType("text/html; charset=UTF-8");
			resp.sendRedirect("/BTL_WEB/web/order");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
