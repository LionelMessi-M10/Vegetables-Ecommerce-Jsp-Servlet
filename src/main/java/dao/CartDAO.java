package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Cart;
import entity.Product;
import entity.User;
import utils.DBConnect;

public class CartDAO {

	private static String getTotalCartByCustomerId = "select sum(ca_it.quantity) from carts ca\r\n"
			+ "inner join cart_items ca_it on ca_it.cart_id = ca.id\r\n"
			+ "where ca.customer_id = ?;";
	private static String getAllCart = "select * from carts";
	private static String getByCustomerId = "select * from carts where customer_id=?";
	private static String getTotalMoneyInCart = "select sum(car_item.quantity * pros.price) from carts car\r\n"
			+ "inner join cart_items car_item on car_item.cart_id = car.id\r\n"
			+ "inner join products pros on pros.id = car_item.product_id\r\n"
			+ "where car.customer_id=?;";
	
	private static String addProductToCart = "insert into carts (customer_id, created_at) values (?,?);";
	private static String deleteById = "delete from carts where id=?;";
	
	public static Long getTotalCartByCustomerId(Long customerId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getTotalCartByCustomerId);
			pre.setLong(1, customerId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) return res.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public static List<Cart> getAllCart() {
		List<Cart> carts = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getAllCart);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Long id = res.getLong("id");
				Long customerId = res.getLong("customer_id");
				Timestamp createdAt = res.getTimestamp("created_at");
				
				carts.add(new Cart(id, createdAt, customerId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carts;
	}
	
	public static Cart getByCustomerId(Long customerId) {
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getByCustomerId);
			pre.setLong(1, customerId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Long id = res.getLong("id");
				Timestamp createdAt = res.getTimestamp("created_at");
				
				return new Cart(id, createdAt, customerId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Long getTotalMoneyInCart(Long customerId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getTotalMoneyInCart);
			
			pre.setLong(1, customerId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) return res.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public static void addProductToCart(Product product, User user) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(addProductToCart);
			pre.setLong(1, user.getId());
			pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteById(Long id) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(deleteById);
			pre.setLong(1, id);
			
			CartItemDAO.deleteByCartId(id);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
