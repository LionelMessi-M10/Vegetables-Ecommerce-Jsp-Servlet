package controller.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.UserDAO;
import entity.User;

@WebServlet(urlPatterns = {"/web/signup", "/web/signin"})
public class LoginRegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/web/signup")) {
			req.setCharacterEncoding("UTF-8");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String phoneNumber = req.getParameter("phoneNumber");
			String address = req.getParameter("address");
			
			User user = new User(null, null, null, firstName, lastName, userName, password, email, phoneNumber, address, "Customer", 1);
			
			try {
				List<User> users = UserDAO.findAll();
				
				HttpSession session = req.getSession();
				
				boolean check = true;
				
				if(!users.isEmpty()) {
					for(User it : users) {
						if(it.getEmail().equals(user.getEmail())) {
							check = false;
							session.setAttribute("emailError", "Email đăng ký đã tồn tại");
							session.setMaxInactiveInterval(20);
							resp.sendRedirect("/BTL_WEB/web/register");
						}
					}
				}
				
				if(check || users.isEmpty()) {
					UserDAO.insertUser(user);
					session.setAttribute("registerSuccess", "Đăng ký thành công. Mời bạn đăng nhập !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if(requestURI.endsWith("/web/signin")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			HttpSession session = req.getSession();
			
			User user = UserDAO.findByEmailAndPassword(email, password);
			
			if(user != null) {
				session.setAttribute("loginedUser", user);
				
				if(user.getStatus() == 1) {
					if(user.getRole().equals("Customer")) {
						if(CartDAO.getTotalCartByCustomerId(user.getId()) > 0) req.setAttribute("totalCart", CartDAO.getTotalCartByCustomerId(user.getId()));
						resp.sendRedirect("/BTL_WEB/web/home");
					}
					else if(user.getRole().equals("Admin")) {
						resp.sendRedirect("/BTL_WEB/admin/dashboard");
					}
				}
				else {
					session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					resp.sendRedirect("/BTL_WEB/web/login");
				}
			}
			
			else {
				session.setAttribute("loginFailed", "Tài khoản chưa chính xác. Hãy đăng nhập lại !");
				resp.sendRedirect("/BTL_WEB/web/login");
			}
		}
	}

	
}
