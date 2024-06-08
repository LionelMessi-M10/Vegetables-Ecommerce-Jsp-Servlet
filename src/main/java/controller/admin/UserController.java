package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

@WebServlet(urlPatterns = {"/admin/user-delete", "/admin/edit-user"})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/admin/user-delete")) {
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
					
					UserDAO.deleteById(id);
					
					resp.sendRedirect("/BTL_WEB/admin/users");
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/edit-user")) {
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
					String firstName = req.getParameter("firstName");
					String lastName = req.getParameter("lastName");
					String userName = req.getParameter("userName");
					String password = req.getParameter("password");
					String email = req.getParameter("email");
					String phoneNumber = req.getParameter("phoneNumber");
					String address = req.getParameter("address");
					String role = req.getParameter("role");
					Integer status = Integer.parseInt(req.getParameter("status"));
					
					User user = new User(id, null, null, firstName, lastName, userName, password, email, phoneNumber, address, role, status);
					
					UserDAO.updateUser(user);
					
					resp.sendRedirect("/BTL_WEB/admin/users");
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
