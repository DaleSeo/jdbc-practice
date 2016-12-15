package seo.dale.practice.jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcTestWithExceptionHandling {

	    static final String URL = "jdbc:h2:mem:test"; // Memory
//		static final String url = "jdbc:h2:~/test"; // Embedded
//		static final String url = "jdbc:h2:tcp://localhost/~/test"; // Server

	@Test
	public void test() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Open a connection
			conn = DriverManager.getConnection(URL);
			System.out.println("Connected to database.");

			// Create a statement
			stmt = conn.createStatement();
			System.out.println("Created statement.");

			// Execute queries
			stmt.executeUpdate("DROP TABLE IF EXISTS TEST");
			stmt.executeUpdate("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
			stmt.executeUpdate("INSERT INTO TEST VALUES(1, 'Hello')");
			stmt.executeUpdate("INSERT INTO TEST VALUES(2, 'World')");
			rs = stmt.executeQuery("SELECT id, name FROM TEST ORDER BY ID");
			System.out.println("Executed query.");

			// Extract data
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("- id: " + id + ", name:" + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					System.out.println("ResultSet closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
					System.out.println("Statement closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
					System.out.println("Connection closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
