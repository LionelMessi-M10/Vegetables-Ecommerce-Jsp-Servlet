package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Cart_Item;
import entity.Product;
import utils.DBConnect;

public class CartItemDAO {

	private static String getByCartId = "select * from cart_items where cart_id=?;";
	private static String getProductFromCartItem = "select * from cart_items cart_it\r\n"
			+ "inner join products pro on pro.id = cart_it.product_id\r\n"
			+ "where cart_it.product_id=?;";
	
	private static String addProductToCartItem = "insert into cart_items (cart_id, product_id, quantity) values (?,?,?);";
	private static String updateCartItem = "update cart_items set quantity=? where cart_id=?;";
	private static String deleteCartItemById = "delete from cart_items where id=?;";
	private static String getById = "select * from cart_items where id=?;";
	private static String deleteByCartId = "delete from cart_items where cart_id=?;";
	
	public static List<Cart_Item> getByCartId(Long cartId) {
		List<Cart_Item> cart_Items = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getByCartId);
			pre.setLong(1, cartId);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				Long id = res.getLong("id");
				Long productId = res.getLong("product_id");
				Long quantity = res.getLong("quantity");
				
				cart_Items.add(new Cart_Item(id, quantity, cartId, productId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cart_Items;
	}
	
	public static Product getProductFromCartItem(Long productId) {
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getProductFromCartItem);
			pre.setLong(1, productId);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
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
				
				return new Product(id, createdAt, modifiedAt, productName, price, quantity, shortDesc, detalDesc, image, status, categoryId);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void addProductToCartItem(Cart_Item cart_Item) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(addProductToCartItem);
			pre.setLong(1, cart_Item.getCartId());
			pre.setLong(2, cart_Item.getProductId());
			pre.setLong(3, cart_Item.getQuantity());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCartItem(Cart_Item cart_Item) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(updateCartItem);
			pre.setLong(1, cart_Item.getQuantity());
			pre.setLong(2, cart_Item.getCartId());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Cart_Item> getById(Long id) {
		List<Cart_Item> cart_Items = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(getById);
			pre.setLong(1, id);
			
			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				Long cartId = res.getLong("cart_id");
				Long productId = res.getLong("product_id");
				Long quantity = res.getLong("quantity");
				
				cart_Items.add(new Cart_Item(id, quantity, cartId, productId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cart_Items;
	}
	
	public static void deleteCartItemById(Long id) {
		List<Cart_Item> cart_Items = getById(id);
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(deleteCartItemById);
			pre.setLong(1, id);
			
			if(cart_Items.size() > 1) {
				pre.executeUpdate();
			}
			else {
				pre.executeUpdate();
				CartDAO.deleteById(cart_Items.get(0).getCartId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteByCartId(Long cartId) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(deleteByCartId);
			pre.setLong(1, cartId);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
