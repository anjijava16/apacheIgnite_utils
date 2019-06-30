package com.mmtechpoc.apacheignite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectIgniteSQLOperation {

	public static void main(String[] args) {

		try {

			// Register JDBC driver
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

			// Open JDBC connection
			Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");

			// Get data
			try (Statement stmt = conn.createStatement()) {
				try (ResultSet rs = stmt.executeQuery(
						"SELECT p.name, c.name " + " FROM Person p, City c " + " WHERE p.city_id = c.id")) {

					while (rs.next())
						System.out.println(rs.getString(1) + ", " + rs.getString(2));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
