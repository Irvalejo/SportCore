import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LigaDAO {
    private DBConnection dbConnection = new DBConnection();

    public void registrarLiga(Liga liga) throws SQLException {
        String sql = "INSERT INTO liga (Nombre, Año) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, liga.getNombre());
            stmt.setInt(2, liga.getAño());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Liga> listarLigas() throws SQLException {
        List<Liga> ligas = new ArrayList<>();
        String sql = "SELECT * FROM liga";

        try (Connection conn = dbConnection.getConnection();
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
            throw(e);
        }

        return ligas;
    }

    public Liga obtenerLigaPorID(int id) throws SQLException {

        String sql = "SELECT * FROM liga WHERE ID = ?";
        try (Connection conn = dbConnection.getConnection();
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
           throw e;
        }
        return null;

    }

    public void actualizarLiga(Liga liga) throws SQLException {
        String sql = "UPDATE liga SET Nombre = ?, Año = ? WHERE ID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, liga.getNombre());
            stmt.setInt(2, liga.getAño());
            stmt.setInt(3, liga.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean eliminarLiga(int id) throws SQLException {
        String sql = "DELETE FROM liga WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas
                return filasAfectadas > 0;
                /*
                if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                    return true; // Si sí, devuelve true
                } else {
                    return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
                }
                */
            } catch (SQLException e) {
               throw e;
            }
    }
}
