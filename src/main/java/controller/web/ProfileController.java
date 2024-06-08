package controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

@WebServlet(urlPatterns = {"/web/save-profile"})
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		
		if(requestUri.endsWith("/web/save-profile")) {
			request.setCharacterEncoding("UTF-8");
			
			Long id = Long.parseLong(request.getParameter("id"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String role = request.getParameter("role");
			Integer status = Integer.parseInt(request.getParameter("status"));
			
			User updateUser = new User(id, null, null, firstName, lastName, userName, password, email, phoneNumber, address, role, status);
			
			HttpSession session = request.getSession();
			
			User loginedUser = (User) session.getAttribute("loginedUser");
			
			if(loginedUser != null) {
				UserDAO.updateUser(updateUser);
				response.sendRedirect("/BTL_WEB/web/home");
			}
			else {
				response.sendRedirect("/BTL_WEB/web/login");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
