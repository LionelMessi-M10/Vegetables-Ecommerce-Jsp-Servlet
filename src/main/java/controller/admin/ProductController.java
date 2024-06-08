package controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ProductDAO;
import dao.UserDAO;
import entity.Product;
import entity.User;

@WebServlet(urlPatterns = {"/admin/add-product", "/admin/product-delete", "/admin/edit-product"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
				 maxFileSize = 1024 * 1024 * 10,      // 10MB
				 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		
		if(requestURI.endsWith("/admin/add-product")) {
			HttpSession session = request.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					respone.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					request.setCharacterEncoding("UTF-8");
			        
			        // Lấy các tham số từ form
			        String name = request.getParameter("name");
			        Long category = Long.parseLong(request.getParameter("category"));
			        Double price = Double.parseDouble(request.getParameter("price"));
			        Long quantity = Long.parseLong(request.getParameter("quantity"));
			        String shortDescription = request.getParameter("shortDescription");
			        String detailedDescription = request.getParameter("detailedDescription");
			        Integer status = Integer.parseInt(request.getParameter("status"));

			        // Lấy file ảnh từ form
			        Part filePart = request.getPart("image");
			        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file

			        // Đường dẫn để lưu file ảnh
			        String uploadPath = getServletContext().getRealPath("/uploads");
			        File uploadDir = new File(uploadPath);
			        if (!uploadDir.exists()) {
			            uploadDir.mkdir();
			        }

			        // Lưu file ảnh vào thư mục upload
			        File file = new File(uploadDir, fileName);
			        filePart.write(file.getAbsolutePath());

			        Product product = new Product(null, null, null, name, price, quantity, shortDescription, detailedDescription, fileName, status, category);

			        ProductDAO.insertProduct(product);
			        // Chuyển hướng đến trang thành công hoặc trang sản phẩm
			        respone.sendRedirect("/BTL_WEB/admin/products");
				}
			} else
				respone.sendRedirect("/BTL_WEB/web/login");
			
		}
		
		else if(requestURI.endsWith("/admin/product-delete")) {
			HttpSession session = request.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					respone.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					request.setCharacterEncoding("UTF-8");
			        
					Long id = Long.parseLong(request.getParameter("id"));
					
					ProductDAO.deleteById(id);
			        
			        // Chuyển hướng đến trang thành công hoặc trang sản phẩm
			        respone.sendRedirect("/BTL_WEB/admin/products");
				}
			} else
				respone.sendRedirect("/BTL_WEB/web/login");
		}
		
		else if(requestURI.endsWith("/admin/edit-product")) {
			HttpSession session = request.getSession();

			User admin = (User) session.getAttribute("loginedUser");

			if (admin != null) {

				User checkAuthorize = UserDAO.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

				if (checkAuthorize.getRole().equals("Customer")) {
					if(checkAuthorize.getStatus() == 0) {
						session.setAttribute("loginFailed", "Tài khoản đã bị khóa. Hãy đăng nhập bằng tài khoản khác !");
					}
					else session.setAttribute("loginFailed", "Không có quyền truy cập. Hãy đăng nhập lại !");
					respone.sendRedirect("/BTL_WEB/web/login");
				}

				else {
					request.setCharacterEncoding("UTF-8");
			        
			        // Lấy các tham số từ form
					Long id = Long.parseLong(request.getParameter("id"));
			        String name = request.getParameter("name");
			        Long category = Long.parseLong(request.getParameter("category"));
			        Double price = Double.parseDouble(request.getParameter("price"));
			        Long quantity = Long.parseLong(request.getParameter("quantity"));
			        String shortDescription = request.getParameter("shortDescription");
			        String detailedDescription = request.getParameter("detailedDescription");
			        Integer status = Integer.parseInt(request.getParameter("status"));

			        // Lấy file ảnh từ form
			        Part filePart = request.getPart("image");
			        String fileName = "";
			        		
			        if(filePart != null && filePart.getSize() > 0) {
			        	fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file

				        // Đường dẫn để lưu file ảnh
				        String uploadPath = getServletContext().getRealPath("/uploads");
				        File uploadDir = new File(uploadPath);
				        if (!uploadDir.exists()) {
				            uploadDir.mkdir();
				        }

				        // Lưu file ảnh vào thư mục upload
				        File file = new File(uploadDir, fileName);
				        filePart.write(file.getAbsolutePath());
			        }
			        
			        Product oldProduct = ProductDAO.getById(id);

			        Product product = new Product(id, null, null, name, price, quantity, shortDescription, detailedDescription, null, status, category);
			        
			        if(!fileName.equals("")) product.setImage(fileName);
			        else product.setImage(oldProduct.getImage());

			        ProductDAO.updateProduct(product);
			        
			        // Chuyển hướng đến trang thành công hoặc trang sản phẩm
			        respone.sendRedirect("/BTL_WEB/admin/products");
				}
			} else
				respone.sendRedirect("/BTL_WEB/web/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
