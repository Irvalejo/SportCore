import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;


    public void abrirConexion() {
        try {
            String URL = "jdbc:mysql://localhost:3306/sportcore";
            String USER = "root";
            String PASSWORD = ""; // Cambia si tu MySQL tiene contraseña

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexion" + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                abrirConexion();
            }
        } catch (SQLException e) {
            System.out.println("Error verificando conexión: " + e.getMessage());
        }
        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion Cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion" + e.getMessage());
        }
    }

}

