package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.Ordered_Product;
import model.dto.OrderDTO;
import model.dto.OrderedProductDTO;
import utils.DBConnect;

public class OrderDAO {

	private static String getTotalSaleProduct = "select sum(ord_pro.quantity) total_sale from orders ord\r\n"
			+ "inner join ordered_product ord_pro on ord_pro.order_id = ord.id where ord.status != 3;";

	private static String insertOrder = "insert into orders (status, created_at, customer_id) values (?,?,?);";
	private static String selectNewOrder = "select top 1 * from orders order by id desc;";
	private static String getByCustomerId = "select * from orders where customer_id=?;";
	private static String getById = "select * from orders where id=?;";
	private static String getAllOrder = "select * from orders;";
	private static String deleteById = "update orders set status=3 where id=?;";
	private static String updateOrder = "update orders set status=? where id=?;";
	private static String getOrdersByDateRange = "SELECT * FROM orders WHERE created_at BETWEEN ? AND ?";

	public static Long getTotalSaleProduct() {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getTotalSaleProduct);
			ResultSet res = pre.executeQuery();
			if (res.next())
				return res.getLong("total_sale");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0L;
	}

	public static Order selectNewOrder() {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(selectNewOrder);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				return new Order(res.getLong("id"), res.getInt("status"), res.getTimestamp("created_at"),
						res.getLong("customer_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void insertOrder(OrderDTO orderDTO) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(insertOrder);
			pre.setInt(1, 0);
			pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pre.setLong(3, orderDTO.getUser().getId());

			pre.executeUpdate();

			Order order = selectNewOrder();

			if (order != null) {
				List<OrderedProductDTO> orderedProductDTOs = orderDTO.getOrderedProductDTOs();
				List<Ordered_Product> ordered_Products = new ArrayList<>();

				for (OrderedProductDTO it : orderedProductDTOs) {
					ordered_Products
							.add(new Ordered_Product(null, it.getQuantity(), order.getId(), it.getProduct().getId()));
				}

				OrderedProductDAO.insertOrderProduct(ordered_Products);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Order> getByCustomerId(Long customerId) {
		List<Order> orders = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getByCustomerId);
			pre.setLong(1, customerId);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				orders.add(new Order(res.getLong("id"), res.getInt("status"), res.getTimestamp("created_at"),
						res.getLong("customer_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public static Order getById(Long orderId) {

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getById);
			pre.setLong(1, orderId);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				return new Order(res.getLong("id"), res.getInt("status"), res.getTimestamp("created_at"),
						res.getLong("customer_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Order> getAll() {
		List<Order> orders = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(getAllOrder);

			ResultSet res = pre.executeQuery();

			while (res.next()) {
				orders.add(new Order(res.getLong("id"), res.getInt("status"), res.getTimestamp("created_at"),
						res.getLong("customer_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public static void deleteById(Long id) {

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(deleteById);
			pre.setLong(1, id);

			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateOrder(Order order) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement pre = conn.prepareStatement(updateOrder);
			pre.setInt(1, order.getStatus());
			pre.setLong(2, order.getId());

			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Order> getOrdersByDateRange(Timestamp fromDate, Timestamp toDate) {
        List<Order> orderList = new ArrayList<>();
        
        Connection conn = DBConnect.getConnection();
        try {
            
        	PreparedStatement pre = conn.prepareStatement(getOrdersByDateRange);
            pre.setTimestamp(1, fromDate);
            pre.setTimestamp(2, toDate);
            ResultSet res = pre.executeQuery();

            while (res.next()) {
            	orderList.add(new Order(res.getLong("id"), res.getInt("status"), res.getTimestamp("created_at"), res.getLong("customer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }
}
