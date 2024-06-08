package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Review;
import utils.DBConnect;

public class ReviewDAO {

	private static String getByProductId = "select * from reivews where product_id=?;";
	private static String insertReview = "insert into reivews (star, created_at, customer_id, product_id, content)\r\n"
			+ "values (?,?,?,?,?);";

	public static List<Review> getByProductId(Long productId) {
		List<Review> reviews = new ArrayList<>();

		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(getByProductId);
			pre.setLong(1, productId);
			
			ResultSet res = pre.executeQuery();
			
			if(res != null) {
				while(res.next()) {
					Long id = res.getLong("id");
					Timestamp createdAt = res.getTimestamp("created_at");
					Timestamp modifiedAt = res.getTimestamp("modified_at");
					String content = res.getString("content");
					Integer star = res.getInt("star");
					Long customerId = res.getLong("customer_id");
					
					reviews.add(new Review(id, createdAt, modifiedAt, content, star, productId, customerId));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviews;
	}
	
	public static void insertReview(Review review) {
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement pre = conn.prepareStatement(insertReview);
			pre.setInt(1, review.getStar());
			pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pre.setLong(3, review.getCustomerId());
			pre.setLong(4, review.getProductId());
			pre.setString(5, review.getContent());
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
