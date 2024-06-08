package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import utils.DBConnect;

public class ProductDAO {

	private static String selectTotalProduct = "select count(id) total_product from products;";

	private static String selectEarnings = "select sum(ord_pro.quantity * pro.price) total_money from orders ord\r\n"
			+ "inner join ordered_product ord_pro on ord.id = ord_pro.order_id\r\n"
			+ "inner join products pro on ord_pro.product_id = pro.id where ord.status != 3;";
	
	private static String insertProduct = "insert into products (price, status, category_id, created_at, quantity, detail_desc, image, product_name, short_desc)\r\n"
			+ "values (?,?,?,?,?,?,?,?,?);";
	
	private static String selectAllProduct = "select * from products where status = 1;";
	
	private static String getProductByCategoryId = "select * from products where category_id=?;";
	
	private static String getById = "select * from products where id=?;";
	
	private static String deleteById = "update products set status = 0 where id=?";
	
	private static String updateProduct = "update products set\r\n"
			+ "price=?, status=?, category_id=?, modified_at=?, quantity=?, detail_desc=?, image=?, product_name=?, short_desc=?\r\n"
			+ "where id=?;";
	
	private static String getProductPage = "select * from products where status = 1 \r\n"
			+ "order by id\r\n"
			+ "offset ? rows fetch next 9 rows only;";
	
	private static String searchProductByKey = "select pro.* from products pro\r\n"
			+ "inner join categories cate on cate.id = pro.category_id\r\n"
			+ "where pro.product_name like ? or cate.category_name like ? and status = 1 \r\n"
			+ "order by pro.id\r\n"
			+ "offset ? rows fetch next 9 rows only;";
	private static String deleteByCategoryId = "update products set status = 0 where category_id=?";
	private static String enableProductByCategoryId = "update products set status = 1 where category_id=?";

	public static Long selectTotalProduct() {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(selectTotalProduct);
			ResultSet res = pre.executeQuery();

			while (res.next()) {
				return res.getLong("total_product");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public static Long selectEarnings() {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(selectEarnings);
			ResultSet res = pre.executeQuery();

			while (res.next()) {
				return res.getLong("total_money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static void insertProduct(Product product) {
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(insertProduct);
			
			pre.setDouble(1, product.getPrice());
			pre.setInt(2, product.getStatus());
			pre.setLong(3, product.getCategoryId());
			pre.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pre.setLong(5, product.getQuantity());
			pre.setString(6, product.getDetailDesc());
			pre.setString(7, product.getImage());
			pre.setString(8, product.getProductName());
			pre.setString(9, product.getShortDesc());
			
			pre.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Product> getAllProduct() {
		List<Product> products = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(selectAllProduct);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				
				Double price = res.getDouble("price");
				Integer status = res.getInt("status");
				Long categoryId = res.getLong("category_id");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				Long quantity = res.getLong("quantity");
				String detalDesc = res.getString("detail_desc");
				String image = res.getString("image");
				String productName = res.getString("product_name");
				String shortDesc = res.getString("short_desc");
				
				products.add(new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	public static List<Product> getProductByCategoryId(Long categoryId) {
		List<Product> products = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getProductByCategoryId);
			
			pre.setLong(1, categoryId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				
				Double price = res.getDouble("price");
				Integer status = res.getInt("status");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				Long quantity = res.getLong("quantity");
				String detalDesc = res.getString("detail_desc");
				String image = res.getString("image");
				String productName = res.getString("product_name");
				String shortDesc = res.getString("short_desc");
				
				products.add(new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	public static Product getById(Long id) {
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getById);
			pre.setLong(1, id);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				
				Double price = res.getDouble("price");
				Integer status = res.getInt("status");
				Long categoryId = res.getLong("category_id");
				Timestamp createdAt = res.getTimestamp("created_at");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				Long quantity = res.getLong("quantity");
				String detalDesc = res.getString("detail_desc");
				String image = res.getString("image");
				String productName = res.getString("product_name");
				String shortDesc = res.getString("short_desc");
				
				return new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void deleteById(Long productId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(deleteById);
			pre.setLong(1, productId);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateProduct(Product product) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(updateProduct);
			
			pre.setDouble(1, product.getPrice());
			pre.setInt(2, product.getStatus());
			pre.setLong(3, product.getCategoryId());
			pre.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pre.setLong(5, product.getQuantity());
			pre.setString(6, product.getDetailDesc());
			pre.setString(7, product.getImage());
			pre.setString(8, product.getProductName());
			pre.setString(9, product.getShortDesc());
			pre.setLong(10, product.getId());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Product> getProductPage(Long page){
		List<Product> products = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getProductPage);
			pre.setLong(1, (page - 1) * 9);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				
				Double price = res.getDouble("price");
				Integer status = res.getInt("status");
				Long categoryId = res.getLong("category_id");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				Long quantity = res.getLong("quantity");
				String detalDesc = res.getString("detail_desc");
				String image = res.getString("image");
				String productName = res.getString("product_name");
				String shortDesc = res.getString("short_desc");
				
				products.add(new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static List<Product> searchProductByKey(String key, Long page) {
		List<Product> products = new ArrayList<>();
		
		key = "%" + key + "%";
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(searchProductByKey);
			pre.setString(1, key);
			pre.setString(2, key);
			pre.setLong(3, page-1);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Double price = res.getDouble("price");
				Integer status = res.getInt("status");
				Long categoryId = res.getLong("category_id");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				Long quantity = res.getLong("quantity");
				String detalDesc = res.getString("detail_desc");
				String image = res.getString("image");
				String productName = res.getString("product_name");
				String shortDesc = res.getString("short_desc");
				
				products.add(new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static void deleteByCategoryId(Long categoryId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(deleteByCategoryId);
			pre.setLong(1, categoryId);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void enableProductByCategoryId(Long categoryId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(enableProductByCategoryId);
			pre.setLong(1, categoryId);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
