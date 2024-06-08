package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	private static Connection connection;

	public static Connection getConnection() {

		try {
			if (connection == null) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=vegetables;encrypt=true;trustServerCertificate=true", "sa", "sa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
