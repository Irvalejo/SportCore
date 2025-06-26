import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sportcore";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Cambia si tu MySQL tiene contraseña


    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador de MySQL", e);
        }
    }

    public void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}




