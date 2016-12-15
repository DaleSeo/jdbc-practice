package seo.dale.practice.jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcTestWithoutExceptionHandling {

	@Test
	public void test() throws SQLException {
		String url = "jdbc:h2:mem:test"; // Memory
//		String url = "jdbc:h2:~/test"; // Embedded
//		String url = "jdbc:h2:tcp://localhost/~/test"; // Server
		Connection conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("DROP TABLE IF EXISTS TEST");
		stmt.executeUpdate("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
		stmt.executeUpdate("INSERT INTO TEST VALUES(1, 'Hello')");
		stmt.executeUpdate("INSERT INTO TEST VALUES(2, 'World')");

		ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ORDER BY ID");

		while (rs.next()) {
			System.out.println(rs.getString(2));
		}

		stmt.executeUpdate("UPDATE TEST SET NAME='Hi' WHERE ID=1");
		stmt.executeUpdate("DELETE FROM TEST WHERE ID=2");

		rs = stmt.executeQuery("SELECT * FROM TEST ORDER BY ID");

		while (rs.next()) {
			System.out.println(rs.getString(2));
		}

		rs.close();
		stmt.close();
		conn.close();
	}

}
