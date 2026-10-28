
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/smart_parking";
        String username = "root";
        String password = "YOUR_MYSQL_PASSWORD";

        return DriverManager.getConnection(url, username, password);
    }
}
