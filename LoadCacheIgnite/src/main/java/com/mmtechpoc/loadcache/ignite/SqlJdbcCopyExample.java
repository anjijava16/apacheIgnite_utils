package com.mmtechpoc.loadcache.ignite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.ignite.internal.util.IgniteUtils;

/**
 * This example demonstrates usage of COPY command via Ignite thin JDBC driver.
 * <p>
 * Ignite nodes must be started in separate process using {@link ExampleNodeStartup} before running this example.
 */
public class SqlJdbcCopyExample {
    /**
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
            executeCommand(conn, "DROP TABLE IF EXISTS City");

            executeCommand(conn,
                "CREATE TABLE City (" +
                "    ID INT(11), " +
                "    Name CHAR(35), " +
                "    CountryCode CHAR(3), " +
                "    District CHAR(20), " +
                "    Population INT(11), " +
                "    PRIMARY KEY (ID, CountryCode) " +
                ") WITH \"template=partitioned, backups=1, affinityKey=CountryCode, CACHE_NAME=City\""
            );

            print("Created database objects.");

            // Load data from CSV file.
            executeCommand(conn, "COPY FROM '" + IgniteUtils.resolveIgnitePath("examples/src/main/resources/sql/city.csv") + "' " +
            "INTO City (ID, Name, CountryCode, District, Population) FORMAT CSV");

            // Read data.
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM City")) {
                    rs.next();

                    print("Populated City table: " + rs.getLong(1) + " entries");
                }
            }

            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT Name, CountryCode FROM City WHERE ID=5")) {
                    rs.next();

                    print("City with ID=5: " + rs.getString(1) + ", " + rs.getString(2));
                }
            }

            // Drop database objects.
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DROP TABLE City");
            }

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