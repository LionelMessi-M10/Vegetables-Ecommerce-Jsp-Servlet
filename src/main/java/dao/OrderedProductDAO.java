package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Ordered_Product;
import utils.DBConnect;

public class OrderedProductDAO {

	private static String getByOrderId = "select * from ordered_product where order_id=?;";
	private static String insertOrderProduct = "insert into ordered_product (order_id, product_id, quantity) values (?,?,?);";
	
	
	public static List<Ordered_Product> getByOrderId(Long orderId) {
		List<Ordered_Product> ordered_Products = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getByOrderId);
			pre.setLong(1, orderId);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Long id = res.getLong("id");
				Long productId = res.getLong("product_id");
				Long quantity = res.getLong("quantity");
				
				ordered_Products.add(new Ordered_Product(id, quantity, orderId, productId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ordered_Products;
	}
	
	public static void insertOrderProduct(List<Ordered_Product> ordered_Products) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(insertOrderProduct);
			
			for(Ordered_Product it : ordered_Products) {
				pre.setLong(1, it.getOrderId());
				pre.setLong(2, it.getProductId());
				pre.setLong(3, it.getQuantity());
				
				pre.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
