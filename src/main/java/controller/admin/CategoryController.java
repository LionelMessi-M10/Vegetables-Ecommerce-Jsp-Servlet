package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import dao.UserDAO;
import entity.Category;
import entity.User;

@WebServlet(urlPatterns = {"/admin/add-category", "/admin/category-delete", "/admin/edit-category"})
public class CategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		
		if(requestURI.endsWith("/admin/add-category")) {
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
					String categoryName = req.getParameter("categoryName");
					
					Category category = new Category(null, categoryName, 1);
					
					CategoryDAO.insertCategory(category);
					resp.sendRedirect("/BTL_WEB/admin/categories");
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
			
		}
		
		else if(requestURI.endsWith("/admin/category-delete")) {
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
					CategoryDAO.deleteByCategoryId(id);
					resp.sendRedirect("/BTL_WEB/admin/categories");
				}
			} else
				resp.sendRedirect("/BTL_WEB/web/login");
			
		}
		
		else if(requestURI.endsWith("/admin/edit-category")) {
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
					String categoryName = req.getParameter("categoryName");
					Integer status = Integer.parseInt(req.getParameter("status"));
					
					Category category = new Category(id, categoryName, status);
					CategoryDAO.updateCategory(category);
					
					resp.sendRedirect("/BTL_WEB/admin/categories");
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
