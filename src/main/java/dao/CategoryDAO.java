package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import utils.DBConnect;

public class CategoryDAO {

	private static String insertCategory = "insert into categories (status, category_name) values (?,?);";
	private static String selectAllCategory = "select * from categories;";
	private static String getByCategoryId = "select * from categories where id=?;";
	private static String getByProductId = "select cates.* from \r\n"
			+ "products pros \r\n"
			+ "inner join categories cates on cates.id = pros.category_id\r\n"
			+ "where pros.id = ? and cates.status = 1;";
	private static String deleteByCategoryId = "update categories set status=0 where id=?";
	private static String updateCategory = "update categories \r\n"
			+ "set status=?, category_name=?\r\n"
			+ "where id=?;";
	
	public static void insertCategory(Category category) {
		
		category.setStatus(1);
		
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(insertCategory);
			
			pre.setInt(1, category.getStatus());
			pre.setString(2, category.getCategoryName());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Category> getAllCategory() {
		List<Category> categories = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(selectAllCategory);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Integer status = res.getInt("status");
				Long id = res.getLong("id");
				String categoryName = res.getString("category_name");
				
				categories.add(new Category(id, categoryName, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categories;
	}
	
	public static Category getByCategoryId(Long categoryId) {
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getByCategoryId);
			pre.setLong(1, categoryId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				return new Category(categoryId, res.getString("category_name"), res.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void deleteByCategoryId(Long categoryId) {
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(deleteByCategoryId);
			pre.setLong(1, categoryId);
			
			ProductDAO.deleteByCategoryId(categoryId);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCategory(Category category) {
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(updateCategory);
			pre.setInt(1, category.getStatus());
			pre.setString(2, category.getCategoryName());
			pre.setLong(3, category.getId());
			
			ProductDAO.enableProductByCategoryId(category.getId());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Category getByProductId(Long productId) {
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(getByProductId);
			pre.setLong(1, productId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Integer status = res.getInt("status");
				Long id = res.getLong("id");
				String categoryName = res.getString("category_name");
				
				return new Category(id, categoryName, status);
			}
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
