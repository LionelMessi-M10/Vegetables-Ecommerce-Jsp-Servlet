package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import utils.DBConnect;

public class UserDAO {

	private static String insertUser = "insert into users ([status], created_at, [address], email, first_name, last_name, [password],\r\n"
			+ "phone_number, [role], username) values\r\n" + "(?,?,?,?,?,?,?,?,?,?);";

	private static String selectAllUser = "select * from users";

	private static String findByEmailAndPassword = "select * from users where email like ? and [password] like ?";

	private static String updateUser = "UPDATE users\r\n" + "SET \r\n" + "	status = ?,\r\n"
			+ "	modified_at = ?,\r\n" + "	address = ?,\r\n" + "	email = ?,\r\n" + "	first_name = ?,\r\n"
			+ "	last_name = ?,\r\n" + "    password = ?,\r\n" + "	phone_number = ?,\r\n" + "	role = ?,\r\n"
			+ "	username = ?\r\n" + "WHERE id = ?;";

	private static String selectTotalCustomer = "select count(id) total_customer from users where role like 'Customer';";
	
	private static String getById = "select * from users where id=?;";
	private static String deleteById = "update users set status = 0 where id=?;";

	public static void insertUser(User user) throws SQLException {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = DBConnect.getConnection();
			pre = conn.prepareStatement(insertUser);

			pre.setInt(1, user.getStatus());
			pre.setTimestamp(2, user.getCreatedAt());
			pre.setString(3, user.getAddress());
			pre.setString(4, user.getEmail());
			pre.setString(5, user.getFirstName());
			pre.setString(6, user.getLastName());
			pre.setString(7, user.getPassword());
			pre.setString(8, user.getPhoneNumber());
			pre.setString(9, user.getRole());
			pre.setString(10, user.getUserName());

			pre.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<User> findAll() {
		List<User> users = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = DBConnect.getConnection();
			pre = conn.prepareStatement(selectAllUser);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				Integer status = res.getInt("status");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				String address = res.getString("address");
				String email = res.getString("email");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String password = res.getString("password");
				String phoneNumber = res.getString("phone_number");
				String role = res.getString("role");
				String username = res.getString("username");

				User user = new User(id, createdAt, modifiedAt, firstName, lastName, username, password, email,
						phoneNumber, address, role, status);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public static User findByEmailAndPassword(String email, String password) {

		User user = null;

		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(findByEmailAndPassword);

			pre.setString(1, email);
			pre.setString(2, password);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				Integer status = res.getInt("status");
				Timestamp createdAt = res.getTimestamp("created_at");
				Long id = res.getLong("id");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				String address = res.getString("address");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String phoneNumber = res.getString("phone_number");
				String role = res.getString("role");
				String username = res.getString("username");

				user = new User(id, createdAt, modifiedAt, firstName, lastName, username, password, email, phoneNumber,
						address, role, status);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public static void updateUser(User user) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(updateUser);

			pre.setInt(1, user.getStatus());
			pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pre.setString(3, user.getAddress());
			pre.setString(4, user.getEmail());
			pre.setString(5, user.getFirstName());
			pre.setString(6, user.getLastName());
			pre.setString(7, user.getPassword());
			pre.setString(8, user.getPhoneNumber());
			pre.setString(9, user.getRole());
			pre.setString(10, user.getUserName());
			pre.setLong(11, user.getId());

			pre.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Long selectTotalCustomer() {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(selectTotalCustomer);


			ResultSet res = pre.executeQuery();
			
			while(res.next()) {
				return res.getLong("total_customer");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public static User getById(Long id) {
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(getById);
			pre.setLong(1, id);
			
			ResultSet res = pre.executeQuery();

			while (res.next()) {
				Integer status = res.getInt("status");
				Timestamp createdAt = res.getTimestamp("created_at");
				Timestamp modifiedAt = res.getTimestamp("modified_at");
				String address = res.getString("address");
				String email = res.getString("email");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String password = res.getString("password");
				String phoneNumber = res.getString("phone_number");
				String role = res.getString("role");
				String username = res.getString("username");

				return new User(id, createdAt, modifiedAt, firstName, lastName, username, password, email,
						phoneNumber, address, role, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteById(Long id) {
		Connection conn = DBConnect.getConnection();
		
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement(deleteById);
			pre.setLong(1, id);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
