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

    public Liga obtenerLigaPorID(int id) {

        String sql = "SELECT * FROM liga WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Liga(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getInt("Año")
                );
            }
        }
        catch (SQLException e) {
            System.out.println("Error al obtener liga: " + e.getMessage());
        }
        return null;

    }

    public void actualizarLiga(Liga liga) {
        String sql = "UPDATE liga SET Nombre = ?, Año = ? WHERE ID = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, liga.getNombre());
            stmt.setInt(2, liga.getAño());
            stmt.setInt(3, liga.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar liga: " + e.getMessage());
        }
    }

    public boolean eliminarLiga(int id) {
        String sql = "DELETE FROM liga WHERE id = ?";

        try (Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas


                if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                    return true; // Si sí, devuelve true
                } else {
                    return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
                }

            } catch (SQLException e) {
                System.out.println("Error al eliminar categoría: " + e.getMessage());
                return false; // Si ocurre un error de SQL, la operación falló
            }
    }
}
