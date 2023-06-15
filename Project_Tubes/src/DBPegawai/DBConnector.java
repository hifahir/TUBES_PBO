package DBPegawai;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnector {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pegawai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private DBConnector() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, 
                                                        USERNAME, 
                                                        PASSWORD);
                connection.setTransactionIsolation(
                        Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Terjadi Kesalahan Koneksi MySQL: " + 
                                    e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

}
