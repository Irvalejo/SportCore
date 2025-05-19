import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LigaDAO {

    public void registrarLiga(Liga liga) {
        String sql = "INSERT INTO liga (Nombre, Año) VALUES (?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, liga.getNombre());
            stmt.setInt(2, liga.getAño());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar liga: " + e.getMessage());
        }
    }

    public List<Liga> listarLigas() {
        List<Liga> ligas = new ArrayList<>();
        String sql = "SELECT * FROM liga";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Liga liga = new Liga();
                liga.setId(rs.getInt("id"));
                liga.setNombre(rs.getString("nombre"));
                liga.setAño(rs.getInt("año"));
                ligas.add(liga);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ligas: " + e.getMessage());
        }

        return ligas;
    }

}
