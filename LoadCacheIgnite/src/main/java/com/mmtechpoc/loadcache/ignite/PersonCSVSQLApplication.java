package com.mmtechpoc.loadcache.ignite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ignite.internal.util.IgniteUtils;

public class PersonCSVSQLApplication {/**
     * Executes JDBC COPY example.
    *
    * @param args Command line arguments, none required.
    * @throws Exception If example execution failed.
    */
   public static void main(String[] args) throws Exception {
       // Open JDBC connection
       try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/")) {
           print("Connected to server.");

           // Create table.
           executeCommand(conn, "DROP TABLE IF EXISTS Person");

           //id1,id2,name1,name2,sal,address1
           executeCommand(conn,
               "CREATE TABLE Person (" +
               "    id1 INT(11), " +
               "    id2 INT(11), " +
               "    name1 VARCHAR, " +
               "    name2 VARCHAR, " +
               "    salary VARCHAR, " +
               "    address1 VARCHAR," +
               "    PRIMARY KEY (id1) " +
               ") WITH \"template=partitioned, backups=1, affinityKey=id1, CACHE_NAME=Person\""
           );

           print("Created database objects.");

           // Load data from CSV file.
           executeCommand(conn, "COPY FROM '" + IgniteUtils.resolveIgnitePath(ConstantsUtils.INPUT_LOAD_CSV) + "' " +
           "INTO Person (id1, id2,name1, name2, salary, address1) FORMAT CSV");

           // Read data.
           try (Statement stmt = conn.createStatement()) {
               try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Person")) {
                   rs.next();

                   print("Populated City table: " + rs.getLong(1) + " entries");
               }
           }

           try (Statement stmt = conn.createStatement()) {
               try (ResultSet rs = stmt.executeQuery("SELECT id1, name1 FROM Person ")) {
                  rs.next();
                   print("Personal Details: " + rs.getInt(1) + ", " + rs.getString(2));
               }
           }
//
//           // Drop database objects.
//           try (Statement stmt = conn.createStatement()) {
//               stmt.executeUpdate("DROP TABLE Person");
//           }

           print("Dropped database objects.");
       }
   }

   /**
    * Prints message.
    *
    * @param msg Message to print before all objects are printed.
    */
   private static void print(String msg) {
       System.out.println();
       System.out.println(">>> " + msg);
   }

   /**
    * Execute SQL command.
    *
    * @param conn Connection.
    * @param sql SQL statement.
    * @throws Exception If failed.
    */
   private static void executeCommand(Connection conn, String sql) throws Exception {
       try (Statement stmt = conn.createStatement()) {
           stmt.executeUpdate(sql);
       }
   }
}